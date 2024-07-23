package com.workshop.demo.service;

import java.util.List;
import java.util.Optional;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.security.UserPrincipal;

public interface RestaurantService {

    // return all the restaurants' names in our blog
    List<String> getAllRestaurantNames(RestaurantRequest restaurantRequest);

    // return the score of one specific restaurant with restaurantRequest
    List<Restaurant> findRestaurantsWithScoreGreaterThanOrEqual(int score);
    // List<Restaurant> getRestaurantsByScore(RestaurantRequest restaurantRequest);

    // check the role of the currentUser and if it is admin, create a restaurant
    Restaurant addRestaurant(RestaurantRequest restaurantRequest, UserPrincipal userPrincipal);

    // delete the restaurant when the user is admin
    ApiResponse deleteRestaurant(UserPrincipal userPrincipal, RestaurantRequest restaurantRequest);

    // Search for a restaurant by name and return the restaurant object
    List<Restaurant> searchByName(String name);

    Optional<Restaurant> searchById(String id);

    List<Restaurant> searchByLocation(String location);

    List<Restaurant> findByCreatedBy(Long createdBy);

    List<Restaurant> searchByNameAndLocation(String name, String location);
}