package com.workshop.demo.service;

import com.workshop.demo.model.Review;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.ReviewRequest;

public interface ReviewService {

    Review addReview(ReviewRequest reviewRequest, Long postId, User currentUser);

    Review getReview(Long postId, Long id);

    Review updateReview(Long postId, Long id, ReviewRequest reviewRequest,
            User currentUser);

    ApiResponse deleteReview(Long postId, Long id, User currentUser);

}