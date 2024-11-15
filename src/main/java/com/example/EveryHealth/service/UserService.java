package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.UserDTO;
import com.example.EveryHealth.entity.UserEntity;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserDTO login(UserDTO userDTO) {
        // 회원가입 때 입력한 email로 해당 user의 정보를 가져옴
        Optional<UserEntity> byEmail = userRepository.findByEmail(userDTO.getEmail());
        // 입력한 email의 user 정보가 있으면
        if(byEmail.isPresent()) {
            UserEntity userEntity = byEmail.get();
            // 사용자가 입력한 password와 회원가입 때 입력한 password를 비교함
            if(userEntity.getPassword().equals(userDTO.getPassword())) {
                // 같으면 해당 user 정보를 dto로 변환하여 return
                return UserDTO.toUserDTO(userEntity);
            } else {
                // password가 다르면 null return
                return null;
            }
        } else {
            // 입력한 email로 저장된 user가 없으면
            // 즉 입력한 email로 회원가입을 하지 않았다면 null return
            return null;
        }
    }
}
