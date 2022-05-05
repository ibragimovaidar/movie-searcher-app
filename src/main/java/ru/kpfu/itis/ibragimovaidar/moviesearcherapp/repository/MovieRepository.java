package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.MovieEntity;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    @Query("SELECT movie FROM MovieEntity movie WHERE movie.genre.id = :genreId")
    Page<MovieEntity> findAllByGenre(UUID genreId, Pageable pageable);
}
