package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.ReviewResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ReviewEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = MovieMapper.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReviewMapper {

    ReviewResponse toResponse(ReviewEntity review);

    List<ReviewResponse> toResponse(List<ReviewEntity> review);
}
