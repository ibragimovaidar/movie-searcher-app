package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieCommentaryRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieCommentaryResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;

import java.util.UUID;

public interface MovieService extends ImageEntityService {

    Page<MovieResponse> findAll(Pageable pageable);

    MovieResponse getById(UUID id);

    Page<MovieResponse> findAllByGenreId(UUID genreId, Pageable pageable);

    MovieResponse save(MovieRequest movieRequest);


    MovieCommentaryResponse createComment(MovieCommentaryRequest movieCommentaryRequest, UUID authorId);
}
