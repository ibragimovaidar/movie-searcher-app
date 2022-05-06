package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.enums.Role;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.RoleEntity;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    Optional<RoleEntity> findByRole(Role role);
}
