package com.workshop.demo.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.core.sync.RequestBody;

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

    private S3Client s3Client;
    private String bucketName = "yelpimagebucket";

    public StorageService() {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_2)
                .build();
    }

    public List<String> listImageUrls(String bucketName) {
        ListObjectsV2Request req = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .maxKeys(10)
                .build();
        ListObjectsV2Response result = s3Client.listObjectsV2(req);

        return result.contents().stream()
                .map(S3Object::key)
                .map(key -> s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(key))
                        .toExternalForm())
                .collect(Collectors.toList());
    }

    public List<String> uploadFiles(MultipartFile... files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                File convFile = convertMultiPartFileToFile(file);
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build(),
                        RequestBody.fromFile(convFile));

                convFile.delete();
                urls.add(s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName))
                        .toExternalForm());
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
