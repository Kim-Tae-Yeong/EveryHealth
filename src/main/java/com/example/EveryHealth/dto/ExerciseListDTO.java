package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.ExerciseListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseListDTO {

    private Long exerciseListId;
    private String exerciseName;
    private String categorize;
    private String exerciseListImageUrl;
    private String description;
    private String storedFileName;

    /**
     * Entity -> DTO 변환 메서드
     * @param exerciseListEntity ExerciseListEntity 객체
     * @return ExerciseListDTO 객체
     */
    public static ExerciseListDTO toExerciseListDTO(ExerciseListEntity exerciseListEntity) {
        ExerciseListDTO dto = new ExerciseListDTO();
        dto.setExerciseListId(exerciseListEntity.getExerciseListId());
        dto.setExerciseName(exerciseListEntity.getExerciseName());
        dto.setCategorize(exerciseListEntity.getCategorize());
        dto.setExerciseListImageUrl(exerciseListEntity.getExerciseListImageUrl());
        dto.setDescription(exerciseListEntity.getDescription());
        dto.setStoredFileName(exerciseListEntity.getStoredFileName());
        return dto;
    }
}
