package com.itechart.springproject.repository;

import com.itechart.springproject.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findById(UUID id);

    Page<UserEntity> findAllByCreatedAtIsNull(Pageable pageable);

    Page<UserEntity> findAll(Pageable pageable);
}
