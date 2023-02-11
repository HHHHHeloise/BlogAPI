package com.workshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.demo.model.Restaurant;
import com.workshop.demo.model.User;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    User findByCreatedBy(Long userId);

}