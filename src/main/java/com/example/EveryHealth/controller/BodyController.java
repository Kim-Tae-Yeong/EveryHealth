package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.BodyDTO;
import com.example.EveryHealth.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myPageBody")
@RequiredArgsConstructor
public class BodyController {
    private final BodyService bodyService;

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<BodyDTO> getBodyInformation(@PathVariable Long userId, @PathVariable String date) {
        BodyDTO bodyInformation = bodyService.getBodyInformation(userId, date);
        return ResponseEntity.ok().body(bodyInformation);
    }

    @PostMapping("/{userId}/{date}")
    public ResponseEntity<String> addBodyInformation(@RequestBody BodyDTO bodyDTO) {
            bodyService.addBodyInformation(bodyDTO);
            return ResponseEntity.ok().body("신체 정보 저장에 성공했습니다.");
    }
}
