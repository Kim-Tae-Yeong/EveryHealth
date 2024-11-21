package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.BodyDTO;
import com.example.EveryHealth.entity.BodyEntity;
import com.example.EveryHealth.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {
    private final BodyService bodyService;

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<BodyDTO> getMyPage(@PathVariable Long userId, @PathVariable String date) {
        BodyDTO bodyInformation = bodyService.getBodyInformation(userId, date);
        return ResponseEntity.ok().body(bodyInformation);
    }

    @PostMapping("/{userId}/{date}")
    public ResponseEntity<String> addInformation(@RequestBody BodyDTO bodyDTO) {
            bodyService.addBodyInformation(bodyDTO);
            return ResponseEntity.ok().body("정보 저장 성공");
    }
}
