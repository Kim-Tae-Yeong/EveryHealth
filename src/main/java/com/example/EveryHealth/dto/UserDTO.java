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
    private Long id;
    private String userEmail;
    private String userName;
    private String nickname;
    private String password;
    private byte[] profileImage;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setPassword(userDTO.getPassword());
        userDTO.setProfileImage(userDTO.getProfileImage());
        return userDTO;
    }
}
