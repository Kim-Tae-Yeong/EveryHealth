package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.ExerciseListDTO;
import com.example.EveryHealth.service.ExerciseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exerciseLists")
public class ExerciseListController {

    private final ExerciseListService exerciseListService;

    @Autowired
    public ExerciseListController(ExerciseListService exerciseListService) {
        this.exerciseListService = exerciseListService;
    }

    /**
     * 모든 운동 데이터를 JSON으로 반환
     * @return List 형태의 운동 데이터 DTO
     */
    @GetMapping
    public ResponseEntity<List<ExerciseListDTO>> getAllExercises() {
        List<ExerciseListDTO> exercises = exerciseListService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }
}
