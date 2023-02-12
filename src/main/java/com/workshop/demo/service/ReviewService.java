package com.workshop.demo.service;

import com.workshop.demo.model.Review;
import com.workshop.demo.payload.ApiResponse;

import com.workshop.demo.payload.ReviewRequest;
import com.workshop.demo.security.UserPrincipal;

public interface ReviewService {
    // get all the reviews of the restaurant
    Review getRestaurantReview(String name);

    Review addReview(ReviewRequest reviewRequest, Long postId, UserPrincipal currentUser);

    // get the user's specific review with userId and the id of the review
    Review getReview(Long userId, Long id);

    // update the user's specific review with userId and the id of the review
    Review updateReview(Long userId, Long id, ReviewRequest reviewRequest,
            UserPrincipal currentUser);

    // delete the user's specific review with userId and the id of the review
    ApiResponse deleteReview(Long userId, Long id, UserPrincipal currentUser);

}