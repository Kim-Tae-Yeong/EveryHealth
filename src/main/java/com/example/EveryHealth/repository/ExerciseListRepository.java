package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.ExerciseListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseListRepository extends JpaRepository<ExerciseListEntity, Long> {
    // 기본 제공되는 CRUD 메서드 사용
}
