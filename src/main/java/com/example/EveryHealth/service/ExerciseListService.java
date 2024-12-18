package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.ExerciseListDTO;
import com.example.EveryHealth.entity.ExerciseListEntity;
import com.example.EveryHealth.repository.ExerciseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseListService {

    private final ExerciseListRepository exerciseListRepository;

    @Autowired
    public ExerciseListService(ExerciseListRepository exerciseListRepository) {
        this.exerciseListRepository = exerciseListRepository;
    }

    /**
     * 모든 운동 데이터를 DTO로 변환하여 반환
     * @return List 형태의 ExerciseListDTO
     */
    public List<ExerciseListDTO> getAllExercises() {
        List<ExerciseListEntity> entities = exerciseListRepository.findAll();
        return entities.stream()
                .map(ExerciseListDTO::toExerciseListDTO) // 변환 메서드 사용
                .collect(Collectors.toList());
    }

    public Resource getImage(String imageUrl) {
        try {
            File file = new File("src/main/resources/static/img/" + imageUrl);
            if(file.exists()) {
                return new FileSystemResource(file);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
