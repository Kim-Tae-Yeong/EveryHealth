package com.example.EveryHealth.entity;

import com.example.EveryHealth.dto.BoardDTO;
import com.example.EveryHealth.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "boards")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", length = 30, nullable = false)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "content", length = 1000)
    private String content;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    public static BoardEntity toBoardEntity(BoardDTO boardDTO, UserRepository userRepository) {
        BoardEntity boardEntity = new BoardEntity();
        Long userId = boardDTO.getUserId();
        UserEntity userEntity = userRepository.findById(userId).get();
        boardEntity.setDate(boardDTO.getDate());
        boardEntity.setUser(userEntity);
        boardEntity.setContent(boardDTO.getContent());

        return boardEntity;
    }
}
