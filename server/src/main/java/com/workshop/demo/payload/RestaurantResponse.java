package com.workshop.demo.payload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.workshop.demo.model.Restaurant;

public class RestaurantResponse {
    Restaurant restaurant;
    boolean success;
    private List<String> messages;
    HttpStatus httpStatus;

    public RestaurantResponse(Restaurant restaurant, boolean success, List<String> messages, HttpStatus httpStatus) {
        this.restaurant = restaurant;
        this.success = success;
        this.messages = messages;
        this.httpStatus = httpStatus;
    }

    public List<String> getMessages() {
        return messages == null ? null : new ArrayList<>(messages);
    }

    public void setMessages(List<String> messages) {
        if (messages == null) {
            this.messages = null;
        } else {
            this.messages = Collections.unmodifiableList(messages);
        }
    }
}
