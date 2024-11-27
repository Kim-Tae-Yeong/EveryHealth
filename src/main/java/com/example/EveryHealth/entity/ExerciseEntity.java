package com.example.EveryHealth.entity;

import com.example.EveryHealth.dto.ExerciseDTO;
import com.example.EveryHealth.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id", length = 30, nullable = false)
    private Long exerciseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "set_count", nullable = false)
    private String setCount;

    @Column(name = "count", nullable = false)
    private String count;

    @Column(name = "weight", nullable = false)
    private String weight;

    public static ExerciseEntity toExerciseEntity(ExerciseDTO exerciseDTO, UserRepository userRepository) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();

        Long userId = exerciseDTO.getUserId();
        UserEntity userEntity = userRepository.findById(userId).get();

        exerciseEntity.setUser(userEntity);
        exerciseEntity.setDate(exerciseDTO.getDate());
        exerciseEntity.setExerciseName(exerciseDTO.getExerciseName());
        exerciseEntity.setSetCount(exerciseDTO.getSetCount());
        exerciseEntity.setCount(exerciseDTO.getCount());
        exerciseEntity.setWeight(exerciseDTO.getWeight());

        return exerciseEntity;
    }
}
