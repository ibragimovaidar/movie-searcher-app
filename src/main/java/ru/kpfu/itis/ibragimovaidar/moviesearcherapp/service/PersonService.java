package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.PersonRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.PersonResponse;

import java.util.UUID;

public interface PersonService extends ImageEntityService {

    PersonResponse getById(UUID id);

    Page<PersonResponse> getAllByMovieId(UUID movieId, Pageable pageable);

    PersonResponse save(PersonRequest personRequest);
}
