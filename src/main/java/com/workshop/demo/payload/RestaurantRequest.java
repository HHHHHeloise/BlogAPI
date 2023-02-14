package com.workshop.demo.payload;

import java.time.Instant;

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

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    private Long createdBy;

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