package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppImageProperties;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.MovieNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.MovieEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.MovieService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn.ImageCdnService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.MovieMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private final ImageCdnService imageCdnService;

    private final AppImageProperties appImageProperties;

    @Override
    public Page<MovieResponse> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movieMapper::movieToMovieResponse);
    }

    @Override
    public MovieResponse getById(UUID id) {
        return movieMapper.movieToMovieResponse(
                movieRepository.findById(id)
                        .orElseThrow(MovieNotFoundException::new)
        );
    }

    @Override
    public Page<MovieResponse> findAllByGenreId(UUID genreId, Pageable pageable) {
        return movieRepository.findAllByGenre(genreId, pageable)
                .map(movieMapper::movieToMovieResponse);
    }

    @Override
    public MovieResponse save(MovieRequest movieRequest) {
        return movieMapper.movieToMovieResponse(
                movieRepository.save(
                        MovieEntity.builder()
                                .name(movieRequest.getName())
                                .description(movieRequest.getDescription())
                                .dateOfRelease(movieRequest.getDateOfRelease())
                                .country(movieRequest.getCountry())
                                .build()
                )
        );
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);
        ImageMetadataEntity imageMetadata = imageCdnService.uploadResizedImage(
                image, appImageProperties.movieImageHeight(), appImageProperties.movieImageWidth());
        movie.setImageMetadata(imageMetadata);
        movieRepository.save(movie);
    }
}
