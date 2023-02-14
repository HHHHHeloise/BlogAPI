package com.workshop.demo.service;

import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

    // return all the restaurants' names in our blog
    List<String> getAllRestaurantNames(RestaurantRequest restaurantRequest);

    // return the score of one specific restaurant with restaurantRequest
    Integer getScore(RestaurantRequest restaurantRequest);

    // check the role of the currentUser and if it is admin, create a restaurant
    Restaurant addRestaurant(RestaurantRequest restaurantRequest, User user);

    // delete the restaurant when the user is admin
    ApiResponse deleteRestaurant(User user, RestaurantRequest restaurantRequest);
}