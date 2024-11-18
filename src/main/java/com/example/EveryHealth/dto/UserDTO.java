package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long userId;
    private String email;
    private String phoneNumber;
    private String name;
    private String nickname;
    private String password;
    private byte[] profileImage;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setName(userEntity.getName());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setProfileImage(userEntity.getProfileImage());
        return userDTO;
    }
}
