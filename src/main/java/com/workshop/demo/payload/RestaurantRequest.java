package com.workshop.demo.payload;

import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestaurantRequest {

    @NotBlank
    @Size(min = 10)
    private String name;

    // private String phone;

    @NotBlank
    @NaturalId
    @Size(max = 40)
    private String email;

    private Long id;

    public String getRestaurantName() {
        return name;
    }

    public String getRestaurantEmail() {
        return email;
    }

    public Long getRestaurantId() {
        return id;
    }

}