package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.ExerciseListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseListRepository extends JpaRepository<ExerciseListEntity, Long> {
    // Spring Data JPA는 기본 CRUD 메서드(findAll, findById 등)를 자동으로 제공
}
