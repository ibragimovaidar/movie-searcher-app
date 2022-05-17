package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.PersonResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.PersonEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonMapper {

    @Mapping(target = "imageUrl", expression = "java(ImageMetadataMapper.imageMetadataToImageUrl(person.getImageMetadata()))")
    PersonResponse personToPersonResponse(PersonEntity person);
}
