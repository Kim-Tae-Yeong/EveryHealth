package com.example.EveryHealth.controller;

import com.example.EveryHealth.dto.BoardDTO;
import com.example.EveryHealth.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 저장
    @PostMapping("/community/save")
    public ResponseEntity<String> save(BoardDTO boardDTO) {
        try{
            boardService.save(boardDTO);
            return ResponseEntity.ok().body("게시글 저장에 성공했습니다.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 전체 게시글 조회
    @GetMapping("/community")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> allBoards = boardService.getAllBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    // 이미지 파일 반환
    @GetMapping("/img/{imageUrl}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageUrl) {
        try {
            Resource resource = boardService.getImage(imageUrl);

            if (resource != null) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 본인 게시글 전체 조회
    @GetMapping("/community/myBoards")
    public ResponseEntity<List<BoardDTO>> getMyAllBoards(@RequestParam Long userId) {
        List<BoardDTO> myAllBoards = boardService.getMyAllBoards(userId);
        return ResponseEntity.ok().body(myAllBoards);
    }

    // 게시글 상세 조회
    @GetMapping("/community/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable Long boardId) {
        try {
            BoardDTO board = boardService.getBoard(boardId);
            return ResponseEntity.ok().body(board);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 게시글 삭제
    @DeleteMapping("/community/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        try {
            boardService.deleteBoard(boardId);
            return ResponseEntity.ok().body("게시글 삭제에 성공했습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
