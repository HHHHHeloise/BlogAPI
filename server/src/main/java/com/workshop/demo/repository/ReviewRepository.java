package com.workshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.workshop.demo.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(String Id);

    @Query("SELECT AVG(r.score) FROM Review r WHERE r.restaurant.id = :restaurantId")
    String findAverageScoreByRestaurantId(String restaurantId);

    List<Review> findByRestaurantId(String restaurantId);

    List<Review> findByUserId(Long userId);

    Long countByUserId(Long userId);
}