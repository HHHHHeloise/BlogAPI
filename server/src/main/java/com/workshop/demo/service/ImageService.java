package com.workshop.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    List<String> uploadImages(String restaurantId, MultipartFile[] files) throws Exception;
}
