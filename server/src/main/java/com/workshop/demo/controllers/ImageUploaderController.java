package com.workshop.demo.controllers;

import com.workshop.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
public class ImageUploaderController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload/{restaurantId}")
    public ResponseEntity<List<String>> uploadImages(@RequestParam("file") MultipartFile[] files) {
        try {
            List<String> fileUrls = storageService.uploadFiles(files);
            if (fileUrls.isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonList("No valid files uploaded."));
            }
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Error uploading files: " + e.getMessage()));
        }
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<String>> getPhotos(@PathVariable String restaurantId,
            @RequestParam(required = false) String bucketName) {
        if (bucketName == null) {
            bucketName = "yelpimagebucket"; // Default bucket name if not specified
        }
        try {
            List<String> imageUrls = storageService.listImageUrls(bucketName);
            if (imageUrls.isEmpty()) {
                System.out.println("No images found in the bucket: " + bucketName);
                return ResponseEntity.noContent().build();
            }
            System.out.println("Retrieved " + imageUrls.size() + " images from bucket: " + bucketName);
            return ResponseEntity.ok(imageUrls);
        } catch (Exception e) {
            System.out.println("Error fetching images from bucket: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Error fetching images: " + e.getMessage()));
        }
    }

}
