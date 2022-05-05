package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.PersonEntity;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {

    @Query("SELECT person FROM PersonEntity person " +
            "JOIN person.movies movie " +
            "WHERE movie.id = :movieId")
    Page<PersonEntity> findAllByMovieId(UUID movieId, Pageable pageable);
}
