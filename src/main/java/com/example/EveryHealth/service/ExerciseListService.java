package com.example.EveryHealth.service;

import com.example.EveryHealth.entity.ExerciseListEntity;
import com.example.EveryHealth.repository.ExerciseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseListService {

    private final ExerciseListRepository exerciseListRepository;

    @Autowired
    public ExerciseListService(ExerciseListRepository exerciseListRepository) {
        this.exerciseListRepository = exerciseListRepository;
    }

    // 모든 운동 목록 조회
    public List<ExerciseListEntity> getAllExercises() {
        return exerciseListRepository.findAll();
    }
}
