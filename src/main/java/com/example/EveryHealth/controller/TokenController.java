package com.example.EveryHealth.controller;

import com.example.EveryHealth.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/validate")
    public ResponseEntity<?> validateAccessToken(HttpServletRequest request) {
        try {
            boolean isValid = tokenService.validateAccessToken(request);
            if (isValid) {
                return ResponseEntity.ok().body(Map.of("valid", true, "message", "Token is valid"));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("valid", false, "message", "Token is expired"));
            }
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("valid", false, "message", "Token is expired"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("valid", false, "message", e.getMessage()));
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request) {
        try {
            String newAccessToken = tokenService.refreshAccessToken(request);
            return ResponseEntity.ok().body(Map.of("accessToken", newAccessToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
