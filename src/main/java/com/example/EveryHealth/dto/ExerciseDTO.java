package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.ExerciseEntity;
import com.example.EveryHealth.entity.UserEntity;
import com.example.EveryHealth.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExerciseDTO {
    private Long exerciseId;
    private Long userId;
    private LocalDate date;
    private String exerciseName;
    private String setCount;
    private String count;
    private String weight;


    public static ExerciseDTO toExerciseDTO(ExerciseEntity exerciseEntity, UserRepository userRepository) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        UserEntity userEntity = userRepository.findById(exerciseEntity.getUser().getUserId()).get();
        Long userId = userEntity.getUserId();

        exerciseDTO.setExerciseId(exerciseEntity.getExerciseId());
        exerciseDTO.setUserId(userId);
        exerciseDTO.setDate(exerciseEntity.getDate());
        exerciseDTO.setExerciseName(exerciseEntity.getExerciseName());
        exerciseDTO.setSetCount(exerciseEntity.getSetCount());
        exerciseDTO.setCount(exerciseEntity.getCount());
        exerciseDTO.setWeight(exerciseEntity.getWeight());

        return exerciseDTO;
    }
}
