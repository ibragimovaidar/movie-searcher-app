package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.ImageMetadataResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ImageMetadataMapper {

    ImageMetadataResponse imageMetadataToImageMetadataResponse(ImageMetadataEntity imageMetadata);
}
