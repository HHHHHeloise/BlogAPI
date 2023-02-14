package com.workshop.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.exception.BlogapiException;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;

import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.service.RestaurantService;

import jakarta.annotation.Resource;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Resource
    private RestaurantRepository restaurantRepository;

    private static final String ID_STR = "id";

    private static final String THIS_RESTAURANT = " this restaurant";

    // return all the restaurants' names in our blog
    @Override
    public List<String> getAllRestaurantNames(RestaurantRequest restaurantRequest) {
        return restaurantRepository.findAllRestaurantNames();
    }

    // return the score of one specific restaurant with restaurantRequest
    @Override
    public Integer getScore(RestaurantRequest restaurantRequest) {
        // Restaurant restaurant =
        // restaurantRepository.findByName(restaurantRequest.getRestaurantName())
        // .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
        // restaurantRequest.getRestaurantName()));
        // return new RestaurantResponse(restaurant, restaurant.getScore());
        return restaurantRepository.getScore(restaurantRequest.getName());
    }

    @Override
    public Restaurant addRestaurant(RestaurantRequest restaurantRequest, User user) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantRequest.getRestaurantName())
                .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
                        restaurantRequest.getRestaurantName()));
        restaurant.setCreatedAt(restaurantRequest.getCreatedAt());
        restaurant.setCreatedBy(restaurantRequest.getCreatedBy());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurant.setId(restaurantRequest.getId());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setName(restaurantRequest.getName());
        restaurant.setPhone(restaurantRequest.getPhone());
        return restaurantRepository.save(restaurant);
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