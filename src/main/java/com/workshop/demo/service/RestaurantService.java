package com.workshop.demo.service;

import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.payload.RestaurantResponse;
import com.workshop.demo.security.UserPrincipal;

public interface RestaurantService {

    // return all the restaurants' names in our blog
    RestaurantResponse<Restaurant> getAllRestaurants();

    // return the score of one specific restaurant with restaurantRequest
    Restaurant getScore(RestaurantRequest restaurantRequest);

    // check the role of the currentUser and if it is admin, create a restaurant
    Restaurant addRestaurant(RestaurantRequest restaurantRequest, User user);

    // delete the restaurant when the user is admin
    ApiResponse deleteRestaurant(User user, RestaurantRequest restaurantRequest);
}