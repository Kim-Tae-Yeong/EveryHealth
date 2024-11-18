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
    private Long user_id;
    private String email;
    private String phone_number;
    private String name;
    private String nickname;
    private String password;
    private byte[] profile_image;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(userEntity.getUser_id());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhone_number(userEntity.getPhone_number());
        userDTO.setName(userEntity.getName());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setProfile_image(userEntity.getProfile_image());
        return userDTO;
    }
}
