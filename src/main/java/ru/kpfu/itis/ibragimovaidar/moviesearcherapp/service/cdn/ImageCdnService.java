package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;

import java.util.UUID;

public interface ImageCdnService {

    String getUrlByUUID(UUID uuid);

    ImageMetadataEntity upload(MultipartFile image);

    ImageMetadataEntity uploadResizedImage(MultipartFile image, int height, int width);
}
