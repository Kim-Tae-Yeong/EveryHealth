package com.example.EveryHealth.entity;

import com.example.EveryHealth.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 30, nullable = false)
    private Long userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 11, nullable = false)
    private String phoneNumber;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "nickname", length = 30)
    private String nickname;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "profile_image")
    @Lob
    private byte[] profileImage;

    public static UserEntity toUserEntity(UserDTO userDTO, String encryptedPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setName(userDTO.getName());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setPassword(encryptedPassword);
        userEntity.setProfileImage(userDTO.getProfileImage());
        return userEntity;
    }
}
