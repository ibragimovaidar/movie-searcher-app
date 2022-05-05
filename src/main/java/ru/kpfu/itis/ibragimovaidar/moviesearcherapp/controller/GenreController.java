package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api.GenreApi;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.GenreRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.GenreResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.GenreService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class GenreController implements GenreApi {

    private final GenreService genreService;

    @Override
    public Page<GenreResponse> getAll(Pageable pageable) {
        return genreService.getAll(pageable);
    }

    @Override
    public GenreResponse create(GenreRequest genreRequest) {
        return genreService.save(genreRequest);
    }

    @Override
    public GenreResponse getById(UUID id) {
        return genreService.getById(id);
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {
        genreService.updateImage(id, image);
    }
}
