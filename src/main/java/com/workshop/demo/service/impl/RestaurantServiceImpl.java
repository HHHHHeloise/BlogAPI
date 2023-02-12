package com.workshop.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateRequestCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.exception.BlogapiException;
import com.workshop.demo.model.Restaurant;

import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.payload.RestaurantResponse;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.repository.ReviewRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String ID_STR = "id";

    private static final String THIS_RESTAURANT = " this restaurant";

    @Override
    public RestaurantResponse<Restaurant> getAllRestaurants() {

        return null;
    }

    @Override
    public Restaurant getScore(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantRequest.getRestaurantName())
                .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
                        restaurantRequest.getRestaurantName()));

        return null;
    }

    @Override
    public Restaurant addRestaurant(RestaurantRequest restaurantRequest, User user) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantRequest.getRestaurantName())
                .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
                        restaurantRequest.getRestaurantName()));
        return null;
    }

    // delete the restaurant when the user is admin
    @Override
    public ApiResponse deleteRestaurant(User user, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantRequest.getRestaurantName())
                .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
                        restaurantRequest.getRestaurantName()));
        String admin = new String("admin");
        if (user.getRole() != admin) {
            return new ApiResponse(Boolean.FALSE, "This is not your restaurant");
        }

        if (user.getRole() == admin) {
            restaurantRepository.deleteById(restaurant.getId());
            return new ApiResponse(Boolean.TRUE, "You successfully deleted review");
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, "You do not have permission to delete this restaurant");
    }
}