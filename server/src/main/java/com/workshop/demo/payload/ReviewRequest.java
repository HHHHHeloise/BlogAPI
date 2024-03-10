package com.workshop.demo.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRequest {

    @NotBlank
    private int score;

    @NotBlank
    @Size(min = 10, message = "Review body must be minimum 10 characters")
    private String body;

    @NotBlank
    private String restaurantName;
}