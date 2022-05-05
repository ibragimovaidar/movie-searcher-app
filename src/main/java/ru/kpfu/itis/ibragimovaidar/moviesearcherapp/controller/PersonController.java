package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api.PersonApi;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.PersonRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.PersonResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.PersonService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PersonController implements PersonApi {

    private final PersonService personService;

    @Override
    public PersonResponse getById(UUID id) {
        return personService.getById(id);
    }

    @Override
    public Page<PersonResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<PersonResponse> getAllByMovieId(UUID movieId, Pageable pageable) {
        return personService.getAllByMovieId(movieId, pageable);
    }

    @Override
    public PersonResponse create(PersonRequest personRequest) {
        return personService.save(personRequest);
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {
        personService.updateImage(id, image);
    }
}
