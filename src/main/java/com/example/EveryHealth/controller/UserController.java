package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.UserDTO;
import com.example.EveryHealth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("save")
    public String register(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return "save";
    }

    // 로그인
    @PostMapping("login")
    public String login(@RequestBody UserDTO userDTO) {
        // 로그인 성공하면 user 정보를, 실패하면 null을 가져옴
        UserDTO loginResult = userService.login(userDTO);
        if(loginResult != null) {
            return "login success";
        } else {
            return "login fail";
        }
    }
}
