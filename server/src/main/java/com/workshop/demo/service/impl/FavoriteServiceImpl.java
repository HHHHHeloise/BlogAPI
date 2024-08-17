package com.workshop.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.model.Favorite;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.FavoriteRequest;
import com.workshop.demo.repository.FavoriteRepository;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private static final String THIS_RESTAURANT = " this restaurant";

    private static final String ID_STR = "id";

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Favorite addFavorite(FavoriteRequest favoriteRequest, UserPrincipal currentUser) {
        validateUser(favoriteRequest.getUserId(), currentUser);

        User user = userRepository.findById(favoriteRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", favoriteRequest.getUserId()));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        Optional<Restaurant> restaurants = restaurantRepository.findById(favoriteRequest.getRestaurantId());
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException(THIS_RESTAURANT, ID_STR, favoriteRequest.getRestaurantId());
        }
        Restaurant restaurant = restaurants.get();
        favorite.setRestaurant(restaurant);
        return favoriteRepository.save(favorite);
    }

    @Override
    public ApiResponse removeFavorite(FavoriteRequest favoriteRequest, UserPrincipal currentUser) {
        validateUser(favoriteRequest.getUserId(), currentUser);
        validateUserRole(currentUser, "customer");

        List<Favorite> favorites = favoriteRepository.findByUserIdAndRestaurantId(
                favoriteRequest.getUserId(), favoriteRequest.getRestaurantId());
        if (favorites.isEmpty()) {
            throw new ResourceNotFoundException("Favorite", "combination of user ID and restaurant ID", "");
        }

        favoriteRepository.deleteAll(favorites);
        return new ApiResponse(Boolean.TRUE, "You successfully removed the favorite");
    }

    private void validateUser(Long userId, UserPrincipal currentUser) {
        if (!currentUser.getId().equals(userId)) {
            throw new IllegalArgumentException("Access Denied: You can only modify your own favorites.");
        }
    }

    private void validateUserRole(UserPrincipal user, String requiredRole) {
        boolean hasRole = user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(requiredRole));
        if (!hasRole) {
            throw new IllegalArgumentException("Access Denied: Insufficient permissions.");
        }
    }

    public List<Favorite> searchByUserId(Long userId, UserPrincipal currentUser) {
        validateUser(userId, currentUser);
        return favoriteRepository.findByUserId(userId);
    }
}
