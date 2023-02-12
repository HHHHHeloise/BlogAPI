package com.workshop.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.exception.BlogapiException;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.Review;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantResponse;
import com.workshop.demo.payload.ReviewRequest;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.repository.ReviewRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {

    private static final String THIS_REVIEW = " this review";

    private static final String THIS_RESTAURANT = " this restaurant";

    private static final String YOU_DON_T_HAVE_PERMISSION_TO = "You don't have permission to ";

    private static final String ID_STR = "id";

    private static final String REVIEW_STR = "Review";

    private static final String REVIEW_DOES_NOT_BELONG_TO_POSTER = "Review does not belong to poster";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // get all the reviews of the restaurant
    @Override
    public Review getRestaurantReview(String restaurantName) {
        return reviewRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, restaurantName));
    }

    @Override
    public Review addReview(ReviewRequest reviewRequest, Long userId, UserPrincipal currentUser) {
        Restaurant restaurant = restaurantRepository.findByName(reviewRequest.getRestaurantName())
                .orElseThrow(() -> new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
                        reviewRequest.getRestaurantName()));
        User user = userRepository.getUser(currentUser);
        Review review = new Review(reviewRequest.getBody());
        review.setUser(user);
        review.setScore(reviewRequest.getScore());
        review.setRestaurant(restaurant);
        return reviewRepository.save(review);
    }

    // get the user's specific review with userId and the id of the review
    @Override
    public Review getReview(Long userId, Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));
        if (review.getUser().getId() == userId) {
            return review;
        }

        throw new BlogapiException(HttpStatus.BAD_REQUEST, REVIEW_DOES_NOT_BELONG_TO_POSTER);
    }

    // update the user's specific review with userId and the id of the review
    @Override
    public Review updateReview(Long userId, Long id, ReviewRequest reviewRequest,
            UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));

        if (review.getUser().getId() != id) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, REVIEW_DOES_NOT_BELONG_TO_POSTER);
        }

        if (review.getUser().getId() == currentUser.getId()
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            review.setBody(reviewRequest.getBody());
            return reviewRepository.save(review);
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "update" + THIS_REVIEW);
    }

    // delete the user's specific review with userId and the id of the review
    @Override
    public ApiResponse deleteReview(Long userId, Long id, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));

        if (review.getUser().getId() != id) {
            return new ApiResponse(Boolean.FALSE, REVIEW_DOES_NOT_BELONG_TO_POSTER);
        }

        if (review.getUser().getId().equals(currentUser.getId())) {
            reviewRepository.deleteById(review.getId());
            return new ApiResponse(Boolean.TRUE, "You successfully deleted review");
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "delete" + THIS_REVIEW);
    }
}