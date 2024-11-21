package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.DietDTO;
import com.example.EveryHealth.service.DietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myPageDiet")
@RequiredArgsConstructor
public class DietController {
    private final DietService dietService;

    @GetMapping("{userId}/{date}")
    public ResponseEntity<DietDTO> getDietInformation(@PathVariable Long userId, @PathVariable String date) {
        DietDTO dietInformation = dietService.getDietInformation(userId, date);
        return ResponseEntity.ok().body(dietInformation);
    }

    @PostMapping("/{userId}/{date}")
    public ResponseEntity<String> addDietInformation(@RequestBody DietDTO dietDTO) {
        dietService.addDietInformation(dietDTO);
        return ResponseEntity.ok().body("정보 저장 성공");
    }
}
