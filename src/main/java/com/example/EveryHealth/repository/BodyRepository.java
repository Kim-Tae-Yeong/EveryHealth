package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.BodyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BodyRepository extends JpaRepository<BodyEntity, Long> {
    @Query("SELECT b FROM BodyEntity b WHERE b.user.userId = :userId AND b.date = :date")
    Optional<BodyEntity> findByUserIdAndDate(Long userId, @Param("date") LocalDate date);
}
