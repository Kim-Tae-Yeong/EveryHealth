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
    @PostMapping("register")
    public String register(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return "register";
    }
}
