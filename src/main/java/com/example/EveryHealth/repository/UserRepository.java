package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 이메일로 user 정보 조회
    Optional<UserEntity> findByEmail(String email);
}
