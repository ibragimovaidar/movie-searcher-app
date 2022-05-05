package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.CommentaryRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.CommentaryResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;

import java.util.UUID;

@RequestMapping("/api/v1/movies")
public interface MovieApi {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<MovieResponse> getAll(Pageable pageable);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byGenre/{genreId}")
    Page<MovieResponse> getAllByGenreId(@PathVariable UUID genreId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    MovieResponse create(@RequestBody MovieRequest movieRequest);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/image")
    void updateImage(@PathVariable UUID id, MultipartFile image);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/commentary")
    CommentaryResponse createCommentary(@PathVariable UUID id, @RequestBody CommentaryRequest commentaryRequest);
}
