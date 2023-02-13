package com.workshop.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import javax.validation.Valid;

import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.security.CurrentUser;
import com.workshop.demo.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{restaurantName}")
    public ResponseEntity<List<Restaurant>> getAllRestaurantNames(
            @Valid @RequestBody RestaurantRequest restaurantRequest) {
        List<Restaurant> restaurant = restaurantService.getAllRestaurantNames(restaurantRequest);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{score}")
    public ResponseEntity<Integer> getScore(
            @Valid @RequestBody RestaurantRequest restaurantRequest) {
        Integer score = restaurantService.getScore(restaurantRequest);

        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest,
            @CurrentUser User user) {
        Restaurant restaurant = restaurantService.addRestaurant(restaurantRequest, user);

        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteRestaurant(@CurrentUser User user,
            @RequestBody RestaurantRequest restaurantRequest) {

        ApiResponse apiResponse = restaurantService.deleteRestaurant(user, restaurantRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}