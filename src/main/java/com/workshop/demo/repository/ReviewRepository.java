package com.workshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.demo.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long Id);

    Optional<Review> findByRestaurantName(String restaurantName);

    List<Review> findByCreatedBy(Long userId);

    Long countByCreatedBy(Long userId);
}