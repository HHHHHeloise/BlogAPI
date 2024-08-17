package com.workshop.demo.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.demo.exception.BadRequestException;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantRequest;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.repository.ReviewRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private static final String ID_STR = "id";

    private static final String THIS_RESTAURANT = " this restaurant";

    // return all the restaurants' names in our blog
    @Override
    public List<String> getAllRestaurantNames(RestaurantRequest restaurantRequest) {
        return restaurantRepository.findAllRestaurantNames();
    }

    @Override
    public Restaurant addRestaurant(RestaurantRequest restaurantRequest, UserPrincipal userPrincipal) {
        List<Restaurant> existingRestaurants = restaurantRepository.findByName(restaurantRequest.getRestaurantName());
        if (!existingRestaurants.isEmpty()) {
            throw new BadRequestException("Has existing restaurant with this name");
        }
        Restaurant restaurant = new Restaurant();
        // restaurant.setCreatedAt(Instant.now());
        restaurant.setCreatedBy(userPrincipal.getId());
        restaurant.setId(restaurantRequest.getId());
        restaurant.setName(restaurantRequest.getName());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurant.setPhone(restaurantRequest.getPhone());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setZipcode(restaurantRequest.getZipcode());
        restaurant.setCuisine(restaurantRequest.getCuisine());
        restaurant.setHours(restaurantRequest.getHours());
        restaurant.setImageUrls(restaurantRequest.getImageUrls());
        restaurant.setWebsite(restaurantRequest.getWebsite());
        restaurant.setMenu(restaurantRequest.getMenu());
        return restaurantRepository.save(restaurant);
    }

    // delete the restaurant when the user is admin
    @Override
    public ApiResponse deleteRestaurant(UserPrincipal userPrincipal, RestaurantRequest restaurantRequest) {
        List<Restaurant> restaurants = restaurantRepository.findByName(restaurantRequest.getRestaurantName());
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException(THIS_RESTAURANT, ID_STR, restaurantRequest.getRestaurantName());
        }
        Restaurant restaurant = restaurants.get(0); // take the first one
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_ADMIN");
        boolean isAdmin = userPrincipal.getAuthorities().stream().anyMatch(auth -> auth.equals(role));
        if (!isAdmin) {
            return new ApiResponse(Boolean.FALSE, "This is not your restaurant");
        }

        restaurantRepository.deleteById(restaurant.getId());
        return new ApiResponse(Boolean.TRUE, "You successfully deleted review");
    }

    @Override
    public List<Restaurant> searchByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Restaurant name cannot be null");
        }
        List<Restaurant> restaurants = restaurantRepository.findByName(name);
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant", "name", name);
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> searchByLocation(String location) {
        System.out.println(location);
        List<Restaurant> restaurants = restaurantRepository.findByLocation(location);
        System.out.println("rest" + restaurants);
        try {
            restaurants = restaurantRepository.findByLocation(location);
            System.out.println("restaurants " + restaurants);
            return restaurants;
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        // if (restaurants.isEmpty()) {
        // throw new ResourceNotFoundException("Restaurant", "location", location);
        // }
        return restaurants;
    }

    @Override
    public List<Restaurant> searchByNameAndLocation(String name, String location) {
        List<Restaurant> restaurants = restaurantRepository.findByName(name);
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant", "location", location);
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> findByCreatedBy(Long createdBy) {
        return restaurantRepository.findByCreatedBy(createdBy);
    }

    // public Optional<Restaurant> searchById(String id) {
    // return restaurantRepository.findById(id);
    // }

    @Override
    public Optional<Restaurant> searchById(String id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        restaurant.ifPresent(r -> {
            String averageScore = reviewRepository.findAverageScoreByRestaurantId(id);
            r.setRating(averageScore);
            restaurantRepository.save(r);
        });
        return restaurant;
    }

    @Override
    public String getAverageScore(String restaurantId) {
        return reviewRepository.findAverageScoreByRestaurantId(restaurantId);
    }
}