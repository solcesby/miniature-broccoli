package com.itechart.springproject.repository;

import com.itechart.springproject.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(
            value = "SELECT * FROM spring.public.users " +
                    "WHERE deleted_at IS NULL",
            nativeQuery = true
    )
    Page<UserEntity> findAllByDeletedAtIsNull(Pageable pageable);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);


}
