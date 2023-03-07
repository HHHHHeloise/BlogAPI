package com.workshop.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.InfoRequest;
import com.workshop.demo.payload.UserProfile;
import com.workshop.demo.security.CurrentUser;
import com.workshop.demo.security.UserPrincipal;

import com.workshop.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/profile/{username}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable(value = "username") String username) {
        UserProfile userProfile = userService.getUserProfile(username);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PostMapping("/v1/auth/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/v1/auth/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username,
            @CurrentUser UserPrincipal currentUser) {
        ApiResponse apiResponse = userService.deleteUser(username, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/v1/auth/setOrUpdateInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfile> setOrUpdateInfo(@CurrentUser UserPrincipal currentUser,
            @Valid @RequestBody InfoRequest infoRequest) {
        UserProfile userProfile = userService.setOrUpdateInfo(currentUser, infoRequest);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

}