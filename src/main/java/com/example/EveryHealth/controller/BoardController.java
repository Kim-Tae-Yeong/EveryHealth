package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.BoardDTO;
import com.example.EveryHealth.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    // 전체 게시글 조회
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> allBoards = boardService.getAllBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    // 본인 게시글 전체 조회
    public ResponseEntity<List<BoardDTO>> getMyAllBoards(@RequestParam Long userId) {
        List<BoardDTO> myAllBoards = boardService.getMyAllBoards(userId);
        return ResponseEntity.ok().body(myAllBoards);
    }

    // 게시글 상세 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable Long boardId) {
        try {
            BoardDTO board = boardService.getBoard(boardId);
            return ResponseEntity.ok().body(board);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
