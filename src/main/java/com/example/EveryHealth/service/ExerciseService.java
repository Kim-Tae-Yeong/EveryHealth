package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.ExerciseDTO;
import com.example.EveryHealth.entity.ExerciseEntity;
import com.example.EveryHealth.repository.ExerciseRepository;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public List<ExerciseDTO> getExerciseInformation(Long userId, String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        Optional<List<ExerciseEntity>> optionalExerciseEntity = exerciseRepository.findByUserIdAndDate(userId, parsedDate);
        if(optionalExerciseEntity.isPresent()) {
            List<ExerciseDTO> exerciseDTOS = new ArrayList<>();
            for(ExerciseEntity exerciseEntity : optionalExerciseEntity.get()) {
                exerciseDTOS.add(ExerciseDTO.toExerciseDTO(exerciseEntity, userRepository));
            }
            return exerciseDTOS;
        } else {
            return null;
        }
    }

    @Transactional
    public void addExerciseInformation(List<ExerciseDTO> exerciseDTOs) {
        LocalDate date = exerciseDTOs.getFirst().getDate();
        Long userId = exerciseDTOs.getFirst().getUserId();

        Optional<List<ExerciseEntity>> existingExerciseEntity = exerciseRepository.findByUserIdAndDate(userId, date);
        existingExerciseEntity.ifPresent(exerciseEntities -> {
            exerciseEntities.forEach(exerciseRepository::delete);
        });

        for(ExerciseDTO exerciseDTO : exerciseDTOs) {
            ExerciseEntity exerciseEntity = ExerciseEntity.toExerciseEntity(exerciseDTO, userRepository);
            exerciseRepository.save(exerciseEntity);
        }
    }
}
