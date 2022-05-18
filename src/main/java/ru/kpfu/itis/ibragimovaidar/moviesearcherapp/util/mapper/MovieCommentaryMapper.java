package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieCommentaryResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.MovieCommentaryEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = UserMapper.class)
public interface MovieCommentaryMapper {

    @Mapping(target = "authorId", source="movieCommentary.author.id")
    @Mapping(target = "username", source="movieCommentary.author.username")
    MovieCommentaryResponse toResponse(MovieCommentaryEntity movieCommentary);
}
