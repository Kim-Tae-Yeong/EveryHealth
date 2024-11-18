package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 이메일로 user 정보 조회
    Optional<UserEntity> findByEmail(String email);

    // 전화번호로 user 정보 조회
    List<UserEntity> findByPhoneNumber(String phoneNumber);
}
