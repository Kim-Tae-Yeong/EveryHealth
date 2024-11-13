package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.UserDTO;
import com.example.EveryHealth.entity.UserEntity;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입 처리
    public void save(UserDTO userDTO) {
        // repository의 메소드를 호출하려면 entity 객체를 넘겨줘야 함
        // 하지만 우리는 dto로 데이터를 전달하기 때문에 이를 entity 객체로 바꿔줘야 함
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }
}
