package com.workshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.workshop.demo.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT name FROM restaurant")
    List<Restaurant> findAllRestaurantNames(Sort sort);

    @Query(value = "SELECT AVG(r.score) FROM review r WHERE r.restaurant_name = :name", nativeQuery = true)
    Integer getScore(@Param("name") String name);

    Optional<Restaurant> findByName(String name);

    List<Restaurant> findByCreatedBy(Long userId);

    List<Restaurant> findByEmail(String email);

    List<Restaurant> findByPhone(String phone);

    List<Restaurant> findByLocation(String location);

}