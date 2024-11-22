package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.ExerciseListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseListRepository extends JpaRepository<ExerciseListEntity, Long> {
    // Primary Key인 exerciseListId로 조회
    @Query("SELECT e FROM ExerciseListEntity e WHERE e.exerciseListId = :exerciseListId")
    Optional<ExerciseListEntity> findByExerciseListId(@Param("exerciseListId") Long exerciseListId);

    // Unique한 운동 이름인 exerciseName으로 조회
    @Query("SELECT e FROM ExerciseListEntity e WHERE e.exerciseName = :exerciseName")
    Optional<ExerciseListEntity> findByExerciseName(@Param("exerciseName") String exerciseName);
}
