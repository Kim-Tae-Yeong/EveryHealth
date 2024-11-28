package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.ExerciseDTO;
import com.example.EveryHealth.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/myPageExercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<List<ExerciseDTO>> getExerciseInformation(@PathVariable Long userId, @PathVariable String date) {
        List<ExerciseDTO> exerciseInformation = exerciseService.getExerciseInformation(userId, date);
        return ResponseEntity.ok().body(exerciseInformation);
    }

    @PostMapping("/{userId}/{date}")
    public ResponseEntity<String> addExerciseInformation(@PathVariable Long userId, @PathVariable LocalDate date, @RequestBody List<ExerciseDTO> exerciseDTOs) {
        exerciseService.addExerciseInformation(userId, date, exerciseDTOs);
        return ResponseEntity.ok().body("운동 정보 저장에 성공했습니다.");
    }
}
