package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppImageProperties;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieCommentaryRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieCommentaryResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.MovieNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.UserNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.MovieEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.UserEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.MovieCommentaryEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.MovieCommentaryRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.MovieService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.cdn.ImageCdnService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.MovieCommentaryMapper;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.MovieMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final UserRepository userRepository;

    private final MovieMapper movieMapper;

    private final MovieCommentaryMapper movieCommentaryMapper;

    private final ImageCdnService imageCdnService;

    private final AppImageProperties appImageProperties;

    private final MovieCommentaryRepository movieCommentaryRepository;

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
    public MovieCommentaryResponse createComment(MovieCommentaryRequest movieCommentaryRequest, UUID authorId) {
        UserEntity user = userRepository.findById(authorId).orElseThrow(UserNotFoundException::new);
        MovieEntity movie = movieRepository.findById(movieCommentaryRequest.getMovieId()).orElseThrow(MovieNotFoundException::new);
        return movieCommentaryMapper.toResponse(
                movieCommentaryRepository.save(MovieCommentaryEntity.builder()
                                .author(user)
                                .movie(movie)
                                .text(movieCommentaryRequest.getText())
                .build()));
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
