package com.workshop.demo.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.workshop.demo.exception.BlogapiException;
import com.workshop.demo.exception.ResourceNotFoundException;
import com.workshop.demo.model.ImageUrl;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.Review;
import com.workshop.demo.model.User;
import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.ReviewRequest;
import com.workshop.demo.repository.ImageRepository;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.repository.ReviewRepository;
import com.workshop.demo.repository.UserRepository;
import com.workshop.demo.security.UserPrincipal;
import com.workshop.demo.service.ReviewService;
import com.workshop.demo.service.StorageService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final String THIS_REVIEW = " this review";

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

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ObjectMapper objectMapper;

    // get all the reviews of the restaurant
    @Override
    public List<Review> findReviewsByRestaurantId(String restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    @Override
    @Transactional
    public Review addReview(UserPrincipal currentUser, String jsonData, MultipartFile[] images) {
        ReviewRequest reviewRequest = parseReviewRequest(jsonData);
        Restaurant restaurant = restaurantRepository.findById(reviewRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", reviewRequest.getRestaurantId()));
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));

        Review review = new Review();
        review.setBody(reviewRequest.getBody());
        review.setUsername(user.getUsername());
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setScore(reviewRequest.getScore());
        review = reviewRepository.save(review);

        if (images != null && images.length > 0) {
            processImages(images, review);
        }

        return review;
    }

    private void processImages(MultipartFile[] images, Review review) {
        try {
            List<String> uploadedImageUrls = storageService.uploadFiles(images);
            List<ImageUrl> imageUrls = uploadedImageUrls.stream()
                    .map(url -> {
                        ImageUrl imageUrl = new ImageUrl();
                        imageUrl.setUrl(url);
                        imageUrl.setReview(review);
                        imageUrl.setRestaurant(review.getRestaurant());
                        return imageUrl;
                    })
                    .collect(Collectors.toList());
            imageRepository.saveAll(imageUrls);
            imageRepository.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload images", e);
        }
    }

    private ReviewRequest parseReviewRequest(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, ReviewRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse jsonData", e);
        }
    }

    // @Override
    // public Review addReview(ReviewRequest reviewRequest, UserPrincipal
    // currentUser) {
    // Optional<Restaurant> restaurants =
    // restaurantRepository.findById(reviewRequest.getRestaurantId());
    // if (restaurants.isEmpty()) {
    // throw new ResourceNotFoundException(THIS_RESTAURANT, ID_STR,
    // reviewRequest.getRestaurantId());
    // }
    // Restaurant restaurant = restaurants.get(); // Assuming we take the first
    // matching restaurant

    // User user = userRepository.getUser(currentUser);
    // Review review = new Review(reviewRequest.getBody());
    // review.setUser(user);
    // review.setUsername(user.getUsername());
    // review.setScore(reviewRequest.getScore());
    // review.setRestaurant(restaurant);
    // return reviewRepository.save(review);
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

    // delete the user's specific review with userId and the id of the review
    @Override
    public ApiResponse deleteReview(Long userId, Long id, UserPrincipal currentUser) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(REVIEW_STR, ID_STR, id));

        if (review.getUser().getId().equals(currentUser.getId())) {
            reviewRepository.deleteById(review.getId());
            return new ApiResponse(Boolean.TRUE, "You successfully deleted review");
        }

        throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "delete" + THIS_REVIEW);
    }
}