package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api.MovieApi;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.CommentaryRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.CommentaryResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.MovieService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class MovieController implements MovieApi {

    private final MovieService movieService;

    @Override
    public Page<MovieResponse> getAll(Pageable pageable) {
        return movieService.findAll(pageable);
    }

    @Override
    public Page<MovieResponse> getAllByGenreId(UUID genreId) {
        return null;
    }

    @Override
    public MovieResponse create(MovieRequest movieRequest) {
        return movieService.save(movieRequest);
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {
        movieService.updateImage(id, image);
    }

    @Override
    public CommentaryResponse createCommentary(UUID id, CommentaryRequest commentaryRequest) {
        return null;
    }
}
