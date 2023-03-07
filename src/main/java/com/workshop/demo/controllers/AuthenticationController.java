package com.workshop.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.demo.config.JwtUtils;
import com.workshop.demo.exception.BadRequestException;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.AuthenticationRequest;
import com.workshop.demo.user.UserDao;
import com.workshop.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    // public AuthenticationController(AuthenticationManager authenticationManager,
    // UserDao userDao, JwtUtils jwtUtils) {
    // this.authenticationManager = authenticationManager;
    // this.jwtUtils = jwtUtils;
    // this.userDao = userDao;
    // }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request) {
        System.out.print("Hello");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        System.out.print("lalalala");
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occured");
    }

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody User user) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            userService.addUser(user);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("user created");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpStatus status = e.getClass().equals(BadRequestException.class) ? HttpStatus.BAD_REQUEST
                    : HttpStatus.INTERNAL_SERVER_ERROR;
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(apiResponse, status);
        }
    }
}