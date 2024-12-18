package com.example.EveryHealth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_files")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_file_id")
    private Long boardFileId;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "stored_file_name")
    private String storedFileName;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName, String imageUrl) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setImageUrl(imageUrl);
        boardFileEntity.setBoardEntity(boardEntity);
        return boardFileEntity;
    }
}
