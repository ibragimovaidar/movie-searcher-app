package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.GenreEntity;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {
}
