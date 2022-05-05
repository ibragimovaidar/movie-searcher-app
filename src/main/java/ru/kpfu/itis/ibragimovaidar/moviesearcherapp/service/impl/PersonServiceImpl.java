package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppImageProperties;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.PersonRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.PersonResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.PersonNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.PersonEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.PersonRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.PersonService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn.ImageCdnService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.PersonMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final ImageCdnService imageCdnService;

    private final AppImageProperties appImageProperties;

    @Override
    public PersonResponse getById(UUID id) {
        return personMapper.personToPersonResponse(
                personRepository.findById(id)
                        .orElseThrow(PersonNotFoundException::new)
        );
    }

    @Override
    public Page<PersonResponse> getAllByMovieId(UUID movieId, Pageable pageable) {
        return personRepository.findAllByMovieId(movieId, pageable)
                .map(personMapper::personToPersonResponse);
    }

    @Override
    public PersonResponse save(PersonRequest personRequest) {
        return personMapper.personToPersonResponse(
                personRepository.save(
                        PersonEntity.builder()
                                .firstName(personRequest.getFirstName())
                                .lastName(personRequest.getLastName())
                                .middleName(personRequest.getMiddleName())
                                .description(personRequest.getDescription())
                                .build()
                )
        );
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {
        PersonEntity person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        ImageMetadataEntity imageMetadata = imageCdnService.uploadResizedImage(
                image, appImageProperties.personImageHeight(), appImageProperties.personImageWidth());
        person.setImageMetadata(imageMetadata);
        personRepository.save(person);
    }
}
