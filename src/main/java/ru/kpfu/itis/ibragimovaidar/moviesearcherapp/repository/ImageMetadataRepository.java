package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ImageMetadataEntity;

import java.util.UUID;

@Repository
public interface ImageMetadataRepository extends JpaRepository<ImageMetadataEntity, UUID> {
}
