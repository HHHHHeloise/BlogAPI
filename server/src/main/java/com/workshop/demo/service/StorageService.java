package com.workshop.demo.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private AmazonS3 s3Client;
    private String bucketName = "yelpimagebucket";

    public StorageService() {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    public List<String> listImageUrls(String bucketName) {
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(10);
        ListObjectsV2Result result = s3Client.listObjectsV2(req);

        return result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .map(key -> s3Client.getUrl(bucketName, key).toString())
                .collect(Collectors.toList());
    }

    public List<String> uploadFiles(MultipartFile... files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                File convFile = convertMultiPartFileToFile(file);
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                s3Client.putObject(new PutObjectRequest(bucketName, fileName, convFile));
                convFile.delete();
                urls.add(s3Client.getUrl(bucketName, fileName).toString());
            }
        }
        return urls;
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.currentTimeMillis() + "_" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

}
