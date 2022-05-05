package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.ImageMetadataResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.ImageNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.ImageUploadException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.ImageMetadataRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn.ImageCdnService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.ImageResizeService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.ImageMetadataMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageCdnServiceCloudinaryImpl implements ImageCdnService {

    private final Cloudinary cloudinary;

    private final ImageMetadataRepository imageMetadataRepository;

    private final ImageResizeService imageResizeService;

    private final ImageMetadataMapper imageMetadataMapper;

    @Override
    public String getUrlByUUID(UUID uuid) {
        return imageMetadataRepository.findById(uuid)
                .orElseThrow(ImageNotFoundException::new)
                .getUrl();
    }

    @Override
    public ImageMetadataEntity upload(MultipartFile image) {
        try {
            String imageUrl = uploadToCloudinaryCdn(image.getInputStream());
            ImageMetadataEntity imageMetadata = ImageMetadataEntity.builder()
                    .url(imageUrl)
                    .build();
            return imageMetadataRepository.save(imageMetadata);
        } catch (IOException e) {
            log.error("", e);
            throw new ImageUploadException();
        }
    }

    @Override
    public ImageMetadataEntity uploadResizedImage(MultipartFile image, int height, int width) {
        try {
            InputStream resizedImage = imageResizeService.resize(image.getInputStream(), height, width);
            String imageUrl = uploadToCloudinaryCdn(resizedImage);
            ImageMetadataEntity imageMetadata = ImageMetadataEntity.builder()
                    .url(imageUrl)
                    .height(height)
                    .width(width)
                    .build();
            return imageMetadataRepository.save(imageMetadata);
        } catch (IOException e) {
            log.error("", e);
            throw new ImageUploadException();
        }
    }

    @SneakyThrows
    private String uploadToCloudinaryCdn(InputStream image){
        String publicId = String.valueOf(UUID.randomUUID());
        cloudinary.uploader().upload(image.readAllBytes(), ObjectUtils.asMap("public_id", publicId));
        return cloudinary.url().generate(publicId);
    }
}
