package com.workshop.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.workshop.demo.model.Favorite;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.FavoriteRequest;
import com.workshop.demo.security.CurrentUser;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.FavoriteService;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse> addFavorite(@Valid @RequestBody FavoriteRequest favoriteRequest,
            @CurrentUser UserPrincipal userPrincipal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Favorite favorite = favoriteService.addFavorite(favoriteRequest, userPrincipal);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Favorite added successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpStatus status = e.getClass().getSimpleName().equals("BadRequestException") ? HttpStatus.BAD_REQUEST
                    : HttpStatus.INTERNAL_SERVER_ERROR;
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(apiResponse, status);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponse> removeFavorite(@Valid @RequestBody FavoriteRequest favoriteRequest,
            @CurrentUser UserPrincipal currentUser) {
        ApiResponse apiResponse = favoriteService.removeFavorite(favoriteRequest, currentUser);
        return new ResponseEntity<>(apiResponse, apiResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable Long userId,
            @CurrentUser UserPrincipal userPrincipal) {
        List<Favorite> favorites = favoriteService.searchByUserId(userId, userPrincipal);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
}
