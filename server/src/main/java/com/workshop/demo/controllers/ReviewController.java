package com.workshop.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.demo.model.Review;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.ReviewRequest;
import com.workshop.demo.security.CurrentUser;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.ReviewService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired

    private ReviewService reviewService;

    @GetMapping("/getWithRestaurantName") // checked
    public ResponseEntity<Review> getRestaurantReview(@Valid @RequestBody ReviewRequest reviewRequest) {
        Review allReviews = reviewService.getRestaurantReview(reviewRequest);
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    @PostMapping("/add")
    // @PreAuthorize("hasRole('USER')") checked
    public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewRequest reviewRequest,
            @PathVariable(name = "userId") Long userId, @CurrentUser UserPrincipal currentUser) {

        Review newReview = reviewService.addReview(reviewRequest, currentUser);

        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    // get the user's specific review with userId and the id of the review
    // review controller @GetMapping("/{id}") checked
    @GetMapping("/getWithUserId/user/{userId}/review/{id}")
    public ResponseEntity<Review> getReview(@PathVariable(name = "userId") Long userId,
            @PathVariable(name = "id") Long id) {
        Review review = reviewService.getReview(userId, id);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/updateWithUserId/user/{userId}/review/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Review> updateReview(@PathVariable(name = "userId") Long userId,
            @PathVariable(name = "id") Long id, @Valid @RequestBody ReviewRequest reviewRequest,
            @CurrentUser UserPrincipal currentUser) {

        Review updatedReview = reviewService.updateReview(userId, id, reviewRequest, currentUser);

        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/deleteWithUserId/user/{userId}/review/{id}")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") checked
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable(name = "userId") Long userId,
            @PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser) {

        ApiResponse response = reviewService.deleteReview(userId, id, currentUser);

        HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(response, status);
    }

}