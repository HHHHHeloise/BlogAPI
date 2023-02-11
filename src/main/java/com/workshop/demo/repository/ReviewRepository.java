package com.workshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.demo.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByCreatedBy(Long userId);
}