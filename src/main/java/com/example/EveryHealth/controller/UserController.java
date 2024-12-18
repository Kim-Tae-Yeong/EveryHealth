package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.UserDTO;
import com.example.EveryHealth.security.JwtUtil;
import com.example.EveryHealth.service.TokenService;
import com.example.EveryHealth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {
        try {
            // 회원가입 처리
            userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            // 이메일 중복 시 400 Bad Request 상태 코드 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // 그 외 에러 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입에 실패했습니다.");
        }
    }

    // 로그인
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        // 로그인 성공하면 user 정보를, 실패하면 null을 가져옴
        UserDTO loginResult = userService.login(userDTO);
        if(loginResult != null) {
            String accessToken = tokenService.generateAccessToken(loginResult.getEmail());
            String refreshToken = tokenService.generateRefreshToken(loginResult.getEmail());

            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60)
                    .sameSite("Strict")
                    .build();

            Map<String, Object> response = new HashMap<>();
            response.put("accessToken", "Bearer " + accessToken);
            response.put("userId", loginResult.getUserId());
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshCookie.toString()).body(response);
        } else {
            return ResponseEntity.status(401).body("로그인에 실패했습니다.");
        }
    }

    // 전화번호로 이메일 찾기
    @PostMapping("emails/search")
    public ResponseEntity<?> findEmailByPhoneNumber(@RequestBody UserDTO userDTO) {
        List<UserDTO> findResults = userService.findEmailByPhoneNumber(userDTO);
        if(!findResults.isEmpty()) {
            List<String> emails = new ArrayList<>();
            for (UserDTO findResult : findResults) {
                String email = findResult.getEmail();
                emails.add(email);
            }
            return ResponseEntity.ok().body(emails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 전화번호로 가입된 이메일이 없습니다.");
        }
    }

    // 비밀번호 변경
    @PostMapping("password")
    public ResponseEntity<?> changePasswordByEmail(@RequestBody UserDTO userDTO) {
        try {
            userService.changePassword(userDTO);
            return ResponseEntity.ok().body("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
