package com.workshop.demo.payload;

import javax.validation.constraints.Size;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestaurantRequest {

    @NotBlank
    @Size(min = 10)
    private String name;

}