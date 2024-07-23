package com.workshop.demo.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.List;

import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.workshop.demo.exception.BlogapiException;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.Review;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.ReviewRequest;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.repository.ReviewRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.ReviewService;

@Service
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
    public Review getRestaurantReview(ReviewRequest reviewRequest) {
        return reviewRepository.findByRestaurantName(reviewRequest.getRestaurantId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(REVIEW_STR, ID_STR, reviewRequest.getRestaurantId()));
    }

    @Override
    public Review addReview(ReviewRequest reviewRequest, UserPrincipal currentUser) {
        Optional<Restaurant> restaurants = restaurantRepository.findById(reviewRequest.getRestaurantId());
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException(THIS_RESTAURANT, ID_STR, reviewRequest.getRestaurantId());
        }
        Restaurant restaurant = restaurants.get(); // Assuming we take the first matching restaurant

        User user = userRepository.getUser(currentUser);
        Review review = new Review(reviewRequest.getBody());
        review.setCreatedAt(Instant.now());
        review.setCreatedBy(user.getId());
        review.setUser(user);
        review.setScore(reviewRequest.getScore());
        review.setRestaurant(restaurant);
        return reviewRepository.save(review);
    }

    // @PostMapping("/add")
    // public ResponseEntity<?> addReview(@RequestBody ReviewRequest reviewRequest,
    // UserPrincipal currentUser) {
    // if (reviewRequest.getScore() == null || reviewRequest.getBody() == null) {
    // return ResponseEntity.badRequest().body("Score and body cannot be null");
    // }

    // User user = (User) authentication.getPrincipal();
    // Review review = new Review();
    // review.setScore(reviewRequest.getScore());
    // review.setBody(reviewRequest.getBody());
    // review.setRestaurantId(reviewRequest.getRestaurantId()); // Ensure this ID is
    // passed in the request
    // review.setUserId(user.getId()); // From logged-in user details
    // reviewRepository.save(review);

    // return ResponseEntity.ok().body("Review added successfully");
    // }

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
    public Review updateReview(Long userId, Long id, ReviewRequest reviewRequest, UserPrincipal currentUser) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));
        Optional<Restaurant> restaurants = restaurantRepository.findById(reviewRequest.getRestaurantId());
        if (review.getUser().getId() != userId) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, REVIEW_DOES_NOT_BELONG_TO_POSTER);
        }

        // if this restaurant does exist, we only take the first one into consideration
        if (review.getUser().getId() == currentUser.getId() && !restaurants.isEmpty()) {
            review.setScore(reviewRequest.getScore());
            review.setBody(reviewRequest.getBody());
            review.setRestaurant(restaurants.get()); // set it to the first restaurant we found
            return reviewRepository.save(review);
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "update" + THIS_REVIEW);
    }

    // @Override
    // public Review updateReview(Long userId, Long id, ReviewRequest reviewRequest,
    // UserPrincipal currentUser) {
    // Review review = reviewRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));
    // Optional<Restaurant> restaurantOptional = restaurantRepository
    // .findByName(reviewRequest.getRestaurantName());
    // if (review.getUser().getId() != id) {
    // throw new BlogapiException(HttpStatus.BAD_REQUEST,
    // REVIEW_DOES_NOT_BELONG_TO_POSTER);
    // }

    // if (review.getUser().getId() == currentUser.getId() &&
    // restaurantOptional.isPresent() == true) {
    // review.setScore(reviewRequest.getScore());
    // review.setBody(reviewRequest.getBody());
    // review.setRestaurant(null);
    // review.setRestaurant(restaurantOptional.get());
    // return reviewRepository.save(review);
    // }

    // throw new BlogapiException(HttpStatus.UNAUTHORIZED,
    // YOU_DON_T_HAVE_PERMISSION_TO + "update" + THIS_REVIEW);
    // }

    // delete the user's specific review with userId and the id of the review
    @Override
    public ApiResponse deleteReview(Long userId, Long id, UserPrincipal currentUser) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));

        // if (review.getUser().getId() != userId) {
        // return new ApiResponse(Boolean.FALSE, REVIEW_DOES_NOT_BELONG_TO_POSTER);
        // }

        if (review.getUser().getId().equals(currentUser.getId())) {
            reviewRepository.deleteById(review.getId());
            return new ApiResponse(Boolean.TRUE, "You successfully deleted review");
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "delete" + THIS_REVIEW);
    }
}