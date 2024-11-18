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
    @Column(length = 30, nullable = false)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 11, nullable = false)
    private String phone_number;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String password;

    @Lob
    private byte[] profile_image;

    public static UserEntity toUserEntity(UserDTO userDTO, String encryptedPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone_number(userDTO.getPhone_number());
        userEntity.setName(userDTO.getName());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setPassword(encryptedPassword);
        userEntity.setProfile_image(userDTO.getProfile_image());
        return userEntity;
    }
}
