package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppImageProperties;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.GenreRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.GenreResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.GenreNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.GenreEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.GenreRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.GenreService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn.ImageCdnService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.GenreMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    private final ImageCdnService imageService;

    private final AppImageProperties appImageProperties;

    @Override
    public Page<GenreResponse> getAll(Pageable pageable) {
        return genreRepository.findAll(pageable).map(genreMapper::genreToGenreResponse);
    }

    @Override
    public GenreResponse getById(UUID id) {
        return genreMapper.genreToGenreResponse(
                genreRepository.findById(id).orElseThrow(GenreNotFoundException::new));
    }

    @Override
    public GenreResponse save(GenreRequest genreRequest) {
        return genreMapper.genreToGenreResponse(
                genreRepository.save(
                        GenreEntity.builder()
                                .name(genreRequest.getName())
                                .description(genreRequest.getDescription())
                                .build()
                )
        );
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {
        GenreEntity genre = genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
        ImageMetadataEntity imageMetadata =
                imageService.uploadResizedImage(image, appImageProperties.genreImageHeight(), appImageProperties.genreImageWidth());
        genre.setImageMetadata(imageMetadata);
        genreRepository.save(genre);
    }
}
