package com.example.EveryHealth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {
    @GetMapping("/{userId}/{date}")
    public ResponseEntity<String> getMyPage(@PathVariable Long userId, @PathVariable String date) {
        return ResponseEntity.ok().body(userId + date);
    }
}
