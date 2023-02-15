package com.workshop.demo.service;

import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.InfoRequest;
import com.workshop.demo.payload.UserProfile;
import com.workshop.demo.security.UserPrincipal;

public interface UserService {

    UserProfile getUserProfile(String username);

    User addUser(User user);

    ApiResponse deleteUser(String userName, UserPrincipal currentUser);

    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest InfoRequest);
}