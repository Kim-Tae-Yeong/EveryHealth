package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.BodyDTO;
import com.example.EveryHealth.entity.BodyEntity;
import com.example.EveryHealth.repository.BodyRepository;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BodyService {
    private final BodyRepository bodyRepository;
    private final UserRepository userRepository;
    public void addBodyInformation(BodyDTO bodyDTO) {
        BodyEntity bodyEntity = BodyEntity.toBodyEntity(bodyDTO, userRepository);
        bodyRepository.save(bodyEntity);
    }
}
