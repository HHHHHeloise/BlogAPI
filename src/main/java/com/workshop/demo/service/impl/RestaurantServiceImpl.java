package com.workshop.demo.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.workshop.demo.exception.BadRequestException;
import com.workshop.demo.exception.BlogapiException;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
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
    public Restaurant addRestaurant(RestaurantRequest restaurantRequest, UserPrincipal userPrincipal) {
        Optional<Restaurant> restaurantOptional = restaurantRepository
                .findByName(restaurantRequest.getRestaurantName());
        if (restaurantOptional.isPresent()) {
            throw new BadRequestException("Has existing restaurant with this name");
        }
        Restaurant restaurant = new Restaurant();
        // final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss");
        // Date d1 = new Date(System.currentTimeMillis());
        // restaurant.setCreatedAt(formatter.format(d1));
        restaurant.setCreatedAt(Instant.now());
        restaurant.setCreatedBy(userPrincipal.getId());
        restaurant.setId(restaurantRequest.getId());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setName(restaurantRequest.getName());
        restaurant.setPhone(restaurantRequest.getPhone());
        return restaurantRepository.save(restaurant);
    }

    // delete the restaurant when the user is admin
    @Override
    public ApiResponse deleteRestaurant(UserPrincipal userPrincipal, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantRequest.getRestaurantName())
                .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
                        restaurantRequest.getRestaurantName()));
        boolean isAdmin = userPrincipal.getAuthorities().stream().anyMatch(auth -> auth.equals("admin"));
        if (!isAdmin) {
            return new ApiResponse(Boolean.FALSE, "This is not your restaurant");
        }

        if (isAdmin) {
            restaurantRepository.deleteById(restaurant.getId());
            return new ApiResponse(Boolean.TRUE, "You successfully deleted review");
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, "You do not have permission to delete this restaurant");
    }
}