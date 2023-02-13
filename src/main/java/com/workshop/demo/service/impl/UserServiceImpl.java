package com.workshop.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workshop.demo.exception.AccessDeniedException;
import com.workshop.demo.exception.BadRequestException;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.InfoRequest;
import com.workshop.demo.payload.UserProfile;
import com.workshop.demo.repository.ReviewRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserProfile getUserProfile(String userName) {
        User user = userRepository.getUserByName(userName);
        Long postCount = reviewRepository.countByCreatedBy(user.getId());

        return new UserProfile(user.getId(), user.getUsername(), user.getCreatedAt(), user.getEmail(), postCount);
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new BadRequestException(apiResponse);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ApiResponse deleteUser(String username, UserPrincipal currentUser) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", username));
        if (!user.getId().equals(currentUser.getId())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE,
                    "You don't have permission to delete profile of: " + username);
            throw new AccessDeniedException(apiResponse);
        }

        userRepository.deleteById(user.getId());

        return new ApiResponse(Boolean.TRUE, "You successfully deleted profile of: " + username);
    }

    @Override
    public UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest) {
        User user = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));

        if (user.getId().equals(currentUser.getId())) {

            User updatedUser = userRepository.save(user);

            Long postCount = reviewRepository.countByCreatedBy(user.getId());

            return new UserProfile(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getCreatedAt(),
                    updatedUser.getEmail(), postCount);
        }

        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update users profile",
                HttpStatus.FORBIDDEN);
        throw new AccessDeniedException(apiResponse);
    }
}