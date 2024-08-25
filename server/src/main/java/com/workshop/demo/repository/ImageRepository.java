package com.workshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workshop.demo.model.ImageUrl;

public interface ImageRepository extends JpaRepository<ImageUrl, Long> {
}
