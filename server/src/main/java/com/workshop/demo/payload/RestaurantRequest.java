package com.workshop.demo.payload;

import java.time.Instant;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestaurantRequest {

    @NotBlank
    @Size(min = 10)
    private String name;
    @NotBlank
    @NaturalId
    @Size(max = 40)
    private String email;
    private String phone;
    private Long id;
    private String location;
    private String zipcode;
    private String cuisine;
    private String hours;
    private String website;
    private String menu;
    private List<String> imageUrls;

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    private Long createdBy;

    public String getMenu() {
        return menu;
    }

    public String getWebsite() {
        return website;
    }

    public String getRestaurantName() {
        return name;
    }

    public String getRestaurantEmail() {
        return email;
    }

    public Long getRestaurantId() {
        return id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getZipcode() {
        return zipcode;
    }
}