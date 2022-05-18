package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.MovieEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {PersonMapper.class, MovieCommentaryMapper.class})
public interface MovieMapper {

    @Mapping(target = "imageUrl", source = "movie.imageMetadata.url")
    @Mapping(target = "genreName", source = "movie.genre.name")
    MovieResponse movieToMovieResponse(MovieEntity movie);
}
