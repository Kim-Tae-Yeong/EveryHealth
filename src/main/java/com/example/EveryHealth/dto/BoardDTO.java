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
    private List<MultipartFile> boardFile;

    // 원본 파일 이름
    private List<String> originalFileName;

    // 서버 저장용 파일 이름
    private List<String> storedFileName;

    public BoardDTO(LocalDateTime date, String content, List<MultipartFile> boardFile) {
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

        List<String> originalFileNameList = new ArrayList<>();
        List<String> storedFileNameList = new ArrayList<>();
        for(BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
            originalFileNameList.add(boardFileEntity.getOriginalFileName());
            storedFileNameList.add(boardFileEntity.getStoredFileName());
        }

        boardDTO.setOriginalFileName(originalFileNameList);
        boardDTO.setStoredFileName(storedFileNameList);

        return boardDTO;
    }
}
