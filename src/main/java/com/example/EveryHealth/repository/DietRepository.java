package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface DietRepository extends JpaRepository<DietEntity, Long> {
    @Query("SELECT b FROM DietEntity b WHERE b.user.userId = :userId AND b.date = :date")
    Optional<DietEntity> findByUserIdAndDate(Long userId, @Param("date") LocalDate parsedDate);
}
