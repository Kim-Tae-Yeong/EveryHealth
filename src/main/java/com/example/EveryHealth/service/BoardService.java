package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.BoardDTO;
import com.example.EveryHealth.entity.BoardEntity;
import com.example.EveryHealth.entity.BoardFileEntity;
import com.example.EveryHealth.repository.BoardFileRepository;
import com.example.EveryHealth.repository.BoardRepository;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final UserRepository userRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDTO, userRepository);
        Long saveId = boardRepository.save(boardEntity).getBoardId();
        BoardEntity board = boardRepository.findById(saveId).get();

        String savePath = new File("src/main/resources/static/img").getAbsolutePath();

        MultipartFile boardFile = boardDTO.getBoardFile();
        String originalFileName = boardFile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String storedFileName = System.currentTimeMillis() + fileExtension;
        File fileToSave = new File(savePath, storedFileName);
        try {
            boardFile.transferTo(fileToSave);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
        }

        String imageUrl = "/img/" + storedFileName;
        BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFileName, storedFileName, imageUrl);
        boardFileRepository.save(boardFileEntity);
    }

    public List<BoardDTO> getAllBoards() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    public List<BoardDTO> getMyAllBoards(Long userId) {
        List<BoardEntity> boardEntityList = boardRepository.findByUser_UserId(userId);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    public BoardDTO getBoard(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        if(optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Optional<BoardEntity> board = boardRepository.findById(boardId);
        if (board.isPresent()) {
            String storedFileName = board.get().getBoardFileEntity().getStoredFileName();
            String basePath = System.getProperty("user.dir");
            Path filePath = Paths.get(basePath, "src", "main", "resources", "static", "img", storedFileName);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                throw new RuntimeException("이미지 삭제 중 오류가 발생했습니다.");
            }
            boardRepository.delete(board.get());
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }

    public Resource getImage(String imageUrl) {
        try {
            File file = new File("src/main/resources/static/img/" + imageUrl);
            if(file.exists()) {
                return new FileSystemResource(file);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
