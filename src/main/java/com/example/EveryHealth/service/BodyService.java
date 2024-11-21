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
        BodyEntity bodyEntity = BodyEntity.toBodyEntity(bodyDTO, userRepository);
        bodyRepository.save(bodyEntity);
    }

    @Transactional
    public BodyDTO getBodyInformation(Long userId, String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        Optional<BodyEntity> optionalBodyEntity = bodyRepository.findByUserIdAndDate(userId, parsedDate);
        if(optionalBodyEntity.isPresent()) {
                // 2. 기존 데이터가 있으면 삭제
            bodyRepository.delete(optionalBodyEntity.get());
            return BodyDTO.toBodyDTO(optionalBodyEntity.get(), userRepository);
        } else {
            return null;
        }
    }
}
