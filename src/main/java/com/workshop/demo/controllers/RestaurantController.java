package com.workshop.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.demo.exception.BadRequestException;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.security.CurrentUser;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/AllRestaurantNames")
    public List<String> getAllRestaurantNames(
            @Valid @RequestBody RestaurantRequest restaurantRequest) {
        List<String> restaurant = restaurantService.getAllRestaurantNames(restaurantRequest);
        return restaurant;
    }

    @GetMapping("/{score}")
    public Integer getScore(
            @Valid @RequestBody RestaurantRequest restaurantRequest) {
        Integer score = restaurantService.getScore(restaurantRequest);

        return score;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest,
            @CurrentUser UserPrincipal userPrincipal) {
        ApiResponse apiResponse = new ApiResponse();
        // RestaurantResponse restaurantResponse = new RestaurantResponse();
        try {
            Restaurant restaurant = restaurantService.addRestaurant(restaurantRequest,
                    userPrincipal);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("restaurant added");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpStatus status = e.getClass().equals(BadRequestException.class) ? HttpStatus.BAD_REQUEST
                    : HttpStatus.INTERNAL_SERVER_ERROR;
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(apiResponse, status);
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ApiResponse deleteRestaurant(@CurrentUser UserPrincipal userPrincipal,
            @RequestBody RestaurantRequest restaurantRequest) {

        ApiResponse apiResponse = restaurantService.deleteRestaurant(userPrincipal, restaurantRequest);
        return apiResponse;
    }
}