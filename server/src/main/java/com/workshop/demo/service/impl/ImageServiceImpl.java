package com.workshop.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;
import com.workshop.demo.model.ImageUrl;
import com.workshop.demo.model.Restaurant;
import com.workshop.demo.repository.ImageRepository;
import com.workshop.demo.repository.RestaurantRepository;
import com.workshop.demo.service.ImageService;
import com.workshop.demo.service.StorageService;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public List<String> uploadImages(String restaurantId, MultipartFile[] files) throws Exception {
        List<String> fileUrls = storageService.uploadFiles(files);
        if (fileUrls.isEmpty()) {
            throw new Exception("No valid files uploaded.");
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<ImageUrl> imageUrls = fileUrls.stream()
                .map(url -> {
                    ImageUrl imageUrl = new ImageUrl();
                    imageUrl.setUrl(url);
                    imageUrl.setRestaurant(restaurant);
                    return imageUrl;
                })
                .collect(Collectors.toList());
        imageRepository.saveAll(imageUrls);
        return fileUrls;
    }
}
