package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.PersonRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.PersonResponse;

import java.util.UUID;

@RequestMapping("/api/v1/persons")
public interface PersonApi {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    PersonResponse getById(@PathVariable UUID id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<PersonResponse> getAll(Pageable pageable);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byMovie/{movieId}")
    Page<PersonResponse> getAllByMovieId(@PathVariable UUID movieId, Pageable pageable);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    PersonResponse create(@RequestBody PersonRequest personRequest);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/image")
    void updateImage(@PathVariable UUID id, MultipartFile image);
}
