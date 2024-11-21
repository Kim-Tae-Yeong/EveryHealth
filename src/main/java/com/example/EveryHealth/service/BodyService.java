package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.BodyDTO;
import com.example.EveryHealth.entity.BodyEntity;
import com.example.EveryHealth.repository.BodyRepository;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyService {
    private final BodyRepository bodyRepository;
    private final UserRepository userRepository;

    public void addBodyInformation(BodyDTO bodyDTO) {
        LocalDate date = bodyDTO.getDate(); // 날짜를 DTO에서 가져오기
        Long userId = bodyDTO.getUserId(); // userId를 DTO에서 가져오기

        // 1. 기존에 해당 날짜의 데이터가 있으면 삭제
        Optional<BodyEntity> existingBodyEntity = bodyRepository.findByUserIdAndDate(userId, date);
        existingBodyEntity.ifPresent(bodyRepository::delete); // 데이터가 있으면 삭제

        // 2. 새로운 데이터 저장
        BodyEntity bodyEntity = BodyEntity.toBodyEntity(bodyDTO, userRepository);
        bodyRepository.save(bodyEntity); // 새로운 데이터 저장
    }

    @Transactional
    public BodyDTO getBodyInformation(Long userId, String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        Optional<BodyEntity> optionalBodyEntity = bodyRepository.findByUserIdAndDate(userId, parsedDate);
        if (optionalBodyEntity.isPresent()) {
            return BodyDTO.toBodyDTO(optionalBodyEntity.get(), userRepository);
        } else {
            return null;
        }
    }
}