package com.workshop.demo.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/AllRestaurantNames")
    public ResponseEntity<List<String>> getAllRestaurantNames(
            @RequestBody(required = false) RestaurantRequest restaurantRequest) {
        List<String> restaurants = restaurantService.getAllRestaurantNames(restaurantRequest);
        if (restaurants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByName(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        try {
            List<Restaurant> restaurants = restaurantService.searchByName(restaurantRequest.getName());
            if (restaurants.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(false, "No restaurant found with the given name"),
                        HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(false, "Failed to search for restaurant: " + e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/searchByLocation")
    public ResponseEntity<?> searchByLocation(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        try {
            List<Restaurant> restaurants = restaurantService.searchByLocation(restaurantRequest.getLocation());
            if (restaurants.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "No restaurant found for the provided location"));
            }
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Internal server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/searchByOwner")
    public ResponseEntity<?> searchByOwner(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        try {
            List<Restaurant> restaurants = restaurantService.findByCreatedBy(restaurantRequest.getCreatedBy());
            if (restaurants.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "No restaurant found for this owner"));
            }
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Internal server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/searchByNameAndLocation")
    public ResponseEntity<?> searchByNameAndLocation(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        try {
            List<Restaurant> restaurants = restaurantService.searchByNameAndLocation(restaurantRequest.getName(),
                    restaurantRequest.getLocation());
            if (restaurants.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "No restaurants found for the given name and location"));
            }
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Failed to search for restaurants: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest,
            @CurrentUser UserPrincipal userPrincipal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
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

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurantById(@PathVariable String restaurantId) {
        try {
            Optional<Restaurant> restaurant = restaurantService.searchById(restaurantId);
            if (!restaurant.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(restaurant.get()); // Returns the restaurant details
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(false, "Failed to retrieve restaurant: " + e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}