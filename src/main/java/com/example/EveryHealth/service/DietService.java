package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.DietDTO;
import com.example.EveryHealth.entity.DietEntity;
import com.example.EveryHealth.repository.DietRepository;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DietService {
    private final DietRepository dietRepository;
    private final UserRepository userRepository;

    public DietDTO getDietInformation(Long userId, String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        Optional<DietEntity> optionalDietEntity = dietRepository.findByUserIdAndDate(userId, parsedDate);
        if(optionalDietEntity.isPresent()) {
            return DietDTO.toDietDTO(optionalDietEntity.get(), userRepository);
        } else {
            return null;
        }
    }

    @Transactional
    public void addDietInformation(DietDTO dietDTO) {
        LocalDate date = dietDTO.getDate();
        Long userId = dietDTO.getUserId();

        Optional<DietEntity> existingDietEntity = dietRepository.findByUserIdAndDate(userId, date);
        if(existingDietEntity.isPresent()) {
            dietRepository.delete(existingDietEntity.get());
        }

        DietEntity dietEntity = DietEntity.toDietEntity(dietDTO, userRepository);
        dietRepository.save(dietEntity);
    }
}
