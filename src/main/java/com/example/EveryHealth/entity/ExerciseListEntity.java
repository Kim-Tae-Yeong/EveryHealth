package com.example.EveryHealth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "exercise_list")
public class ExerciseListEntity {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 운동 이름 (최대 50자)
    @Column(name = "exercise_name", nullable = false, unique = true, length = 50)
    private String exerciseName;

    // 운동 카테고리 (최대 30자)
    @Column(name = "categorize", nullable = false, length = 30)
    private String categorize; // 예: 등운동, 어깨운동, 팔운동

    // 운동 이미지 (GIF URL)
    @Column(name = "image_url", nullable = false, length = 1000)
    private String imageUrl;

    // 운동 설명
    @Column(name = "description", nullable = false, length = 1000)
    private String description; // 운동 방법 및 설명
}

