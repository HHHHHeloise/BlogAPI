package com.workshop.demo.payload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class RestaurantResponse<T> {
    private String name;

    private List<T> review;

    private boolean last;

    public RestaurantResponse() {

    }

    public RestaurantResponse(List<T> review, boolean last) {
        setReview(review);
        this.last = last;
    }

    public List<T> getAllReviews() {
        return review == null ? null : new ArrayList<>(review);
    }

    public final void setReview(List<T> content) {
        if (review == null) {
            this.review = null;
        } else {
            this.review = Collections.unmodifiableList(review);
        }
    }

    public boolean isLast() {
        return last;
    }
}