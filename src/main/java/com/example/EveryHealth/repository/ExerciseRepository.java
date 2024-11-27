package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Query("SELECT b FROM ExerciseEntity b WHERE b.user.userId = :userId AND b.date = :date")
    Optional<List<ExerciseEntity>> findByUserIdAndDate(Long userId, @Param("date") LocalDate parsedDate);
}
