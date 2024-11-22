package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.BoardDTO;
import com.example.EveryHealth.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 저장
    @PostMapping("/save")
    public ResponseEntity<String> save(BoardDTO boardDTO) {
        try{
            boardService.save(boardDTO);
            return ResponseEntity.ok().body("정보 저장 성공");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
