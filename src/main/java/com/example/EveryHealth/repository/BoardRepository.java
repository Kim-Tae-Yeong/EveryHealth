package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByUser_UserId(Long userId);
}
