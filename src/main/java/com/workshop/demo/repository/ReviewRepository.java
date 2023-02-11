package com.workshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.demo.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long Id);

    Optional<Review> findByRestaurantName(String restaurantName);

    List<Review> findByCreatedBy(Long userId);

    List<Review> deleteReview(Long userId);

    List<Review> createReview(Long userId);
}