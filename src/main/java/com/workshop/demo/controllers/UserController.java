package com.workshop.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

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

    @DeleteMapping("/users/delete/{username}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username,
            @CurrentUser UserPrincipal currentUser) {
        System.out.print("Hhhhhh");
        ApiResponse apiResponse = userService.deleteUser(username, currentUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/users/update/setOrUpdateInfo")
    public ResponseEntity<UserProfile> setOrUpdateInfo(@CurrentUser UserPrincipal currentUser,
            @Valid @RequestBody InfoRequest infoRequest) {
        UserProfile userProfile = userService.setOrUpdateInfo(currentUser, infoRequest);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

}