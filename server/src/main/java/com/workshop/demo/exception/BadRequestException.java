package com.workshop.demo.exception;

import com.workshop.demo.payload.ApiResponse;
import com.workshop.demo.payload.RestaurantResponse;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ApiResponse apiResponse;

    private RestaurantResponse restaurantResponse;

    public BadRequestException(ApiResponse apiResponse, RestaurantResponse restaurantResponse) {
        super();
        this.apiResponse = apiResponse;
        this.restaurantResponse = restaurantResponse;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }

    public RestaurantResponse getRestaurantResponse() {
        return restaurantResponse;
    }
}