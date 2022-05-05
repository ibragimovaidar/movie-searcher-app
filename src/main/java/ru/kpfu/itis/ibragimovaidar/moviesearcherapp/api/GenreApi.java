package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.GenreRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.GenreResponse;

import java.util.UUID;

@RequestMapping("/api/v1/genres")
public interface GenreApi {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<GenreResponse> getAll(Pageable pageable);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    GenreResponse create(@RequestBody GenreRequest genreRequest);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    GenreResponse getById(@PathVariable UUID id);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/image")
    void updateImage(@PathVariable UUID id, MultipartFile image);
}
