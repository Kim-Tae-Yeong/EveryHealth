package com.example.EveryHealth.service;

import com.example.EveryHealth.dto.BoardDTO;
import com.example.EveryHealth.entity.BoardEntity;
import com.example.EveryHealth.entity.BoardFileEntity;
import com.example.EveryHealth.repository.BoardFileRepository;
import com.example.EveryHealth.repository.BoardRepository;
import com.example.EveryHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        /*
         * 1. DTO에 담긴 파일을 꺼냄
         * 2. 파일의 이름 가져옴
         * 3. 서버 저장용 이름을 만듦
         * - 내사진.jpg -> 88283110421_내사진.jpg
         * 4. 저장 경로 설정
         * 5. 해당 경로에 파일 저장
         * 6. board_table에 해당 데이터 save 처리
         * 7. board_file_table에 해당 데이터 save 처리
         */
        BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDTO, userRepository);
        Long saveId = boardRepository.save(boardEntity).getBoardId();
        BoardEntity board = boardRepository.findById(saveId).get();

        String savePath = new File("src/main/resources/static/img").getAbsolutePath();

        // 디렉토리가 없으면 생성
        File directory = new File(savePath);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        for(MultipartFile boardFile : boardDTO.getBoardFile()) {
            String originalFileName = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

            File fileToSave = new File(savePath, storedFileName);
            try {
                boardFile.transferTo(fileToSave);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 중 오류 발생가 발생했습니다.");
            }

            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFileName, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
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

    public void deleteBoard(Long boardId) {
        Optional<BoardEntity> board = boardRepository.findById(boardId);
        if (board.isPresent()) {
            boardRepository.delete(board.get());
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }
}
