package com.workshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.workshop.demo.model.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findByUserId(Long userId);

    List<Favorite> findByUserIdAndRestaurantId(Long userId, Long restaurantId);
}
