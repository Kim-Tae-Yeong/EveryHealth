package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.BoardEntity;
import com.example.EveryHealth.entity.BoardFileEntity;
import com.example.EveryHealth.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Long boardId;
    private Long userId;
    private LocalDateTime date;
    private String content;

    // 파일을 담는 용도
    private MultipartFile boardFile;

    // 원본 파일 이름
    private String originalFileName;

    // 서버 저장용 파일 이름
    private String storedFileName;

    // 이미지 URL
    private String imageUrl;

    public BoardDTO(LocalDateTime date, String content, MultipartFile boardFile) {
        this.date = date;
        this.content = content;
        this.boardFile = boardFile;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setUserId(boardEntity.getUser().getUserId());
        boardDTO.setDate(boardEntity.getDate());
        boardDTO.setContent(boardEntity.getContent());

        BoardFileEntity boardFileEntity = boardEntity.getBoardFileEntity();
        boardDTO.setOriginalFileName(boardFileEntity.getOriginalFileName());
        boardDTO.setStoredFileName(boardFileEntity.getStoredFileName());
        boardDTO.setImageUrl(boardFileEntity.getImageUrl());

        return boardDTO;
    }
}
