package com.workshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.workshop.demo.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT name FROM restaurant", nativeQuery = true)
    List<String> findAllRestaurantNames();

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(:name) AND LOWER(r.location) LIKE LOWER(:location)")
    List<Restaurant> findByNameAndLocationIgnoreCase(String name, String location);

    @Query("SELECT r FROM Restaurant r WHERE r.name = ?1")
    List<Restaurant> findByName(String name);

    Optional<Restaurant> findById(String id);

    List<Restaurant> findByCreatedBy(Long createdBy);

    List<Restaurant> findByEmail(String email);

    List<Restaurant> findByPhone(String phone);

    List<Restaurant> findByLocation(String location);

}