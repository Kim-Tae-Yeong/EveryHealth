package com.example.EveryHealth.entity;

import com.example.EveryHealth.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 30, nullable = false)
    private Long id;

    @Column(length = 30)
    private String userName;

    @Column(length = 30)
    private String nickname;

    @Column(length = 30)
    private String password;

    @Lob
    private byte[] profileImage;

    public static UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setProfileImage(userDTO.getProfileImage());
        return userEntity;
    }
}
