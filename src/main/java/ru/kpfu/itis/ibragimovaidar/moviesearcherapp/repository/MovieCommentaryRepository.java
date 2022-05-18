package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.MovieCommentaryEntity;

import java.util.UUID;

public interface MovieCommentaryRepository extends JpaRepository<MovieCommentaryEntity, UUID> {
}
