package com.workshop.demo.payload;

public class FavoriteRequest {
    private Long userId;
    private Long restaurantId;

    // Constructors
    public FavoriteRequest() {
    }

    public FavoriteRequest(Long userId, Long restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
