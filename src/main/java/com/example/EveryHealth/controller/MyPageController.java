package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.BodyDTO;
import com.example.EveryHealth.entity.BodyEntity;
import com.example.EveryHealth.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {
    private final BodyService bodyService;

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<String> getMyPage(@PathVariable Long userId, @PathVariable String date) {
        return ResponseEntity.ok().body("마이페이지");
    }

    @PostMapping("/{userId}/{date}")
    public ResponseEntity<String> addBodyInformation(@PathVariable Long userId, @PathVariable String date, @RequestBody BodyDTO bodyDTO) {
            bodyService.addBodyInformation(bodyDTO);
            return ResponseEntity.ok().body("정보 저장 성공");
    }
}
