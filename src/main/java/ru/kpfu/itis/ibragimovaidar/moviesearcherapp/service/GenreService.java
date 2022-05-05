package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.GenreRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.GenreResponse;

import java.util.UUID;

public interface GenreService extends ImageEntityService {

    Page<GenreResponse> getAll(Pageable pageable);

    GenreResponse getById(UUID uuid);

    GenreResponse save(GenreRequest genreRequest);
}
