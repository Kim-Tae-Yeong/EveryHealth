package com.example.EveryHealth.repository;

import com.example.EveryHealth.entity.ExerciseListEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseListRepository extends JpaRepository<ExerciseListEntity, Long> {

    /**
     * 모든 운동 데이터를 Slice로 가져옵니다.
     * @param pageable 페이징 정보를 포함하는 객체
     * @return 운동 데이터의 Slice
     */
    Slice<ExerciseListEntity> findAllBy(Pageable pageable);
}
