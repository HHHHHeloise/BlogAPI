package com.workshop.demo.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.workshop.demo.model.Review;
import com.workshop.demo.payload.ApiResponse;

import com.workshop.demo.payload.ReviewRequest;
import com.workshop.demo.security.UserPrincipal;

@Component
@Mapper
public interface ReviewService {
    // get all the reviews of the restaurant
    // review controller @PostMapping("/api/reviews")
    Review getRestaurantReview(String name);

    // review controller @PostMapping
    Review addReview(ReviewRequest reviewRequest, Long userId, UserPrincipal currentUser);

    // get the user's specific review with userId and the id of the review
    // review controller @GetMapping("/{id}")
    Review getReview(Long userId, Long id);

    // update the user's specific review with userId and the id of the review
    // review controller @PutMapping("/{id}")
    Review updateReview(Long userId, Long id, ReviewRequest reviewRequest,
            UserPrincipal currentUser);

    // delete the user's specific review with userId and the id of the review
    // review controller @DeleteMapping("/{id}")
    ApiResponse deleteReview(Long userId, Long id, UserPrincipal currentUser);

}