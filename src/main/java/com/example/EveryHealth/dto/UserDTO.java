package com.example.EveryHealth.dto;

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
    private String userName;
    private String nickname;
    private String password;
    private byte[] profileImage;
}
