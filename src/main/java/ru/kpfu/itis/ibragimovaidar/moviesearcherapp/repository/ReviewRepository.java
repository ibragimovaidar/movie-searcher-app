package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.ReviewEntity;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    List<ReviewEntity> findAllByAuthorId(UUID authorId);
}
