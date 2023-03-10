package com.workshop.demo.payload;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private Long id;
    private String username;
    private Instant joinedAt;
    private String email;
    private Long postCount;
}