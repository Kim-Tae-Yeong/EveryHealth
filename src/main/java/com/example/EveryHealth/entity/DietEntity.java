package com.example.EveryHealth.entity;

import com.example.EveryHealth.dto.DietDTO;
import com.example.EveryHealth.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "diet")
public class DietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id", length = 30, nullable = false)
    private Long dietId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "breakfast", length = 1000)
    private String breakfast;

    @Column(name = "lunch", length = 1000)
    private String lunch;

    @Column(name = "dinner", length = 1000)
    private String dinner;

    public static DietEntity toDietEntity(DietDTO dietDTO, UserRepository userRepository) {
        DietEntity dietEntity = new DietEntity();
        Long userId = dietDTO.getUserId();
        UserEntity userEntity = userRepository.findById(userId).get();

        dietEntity.setUser(userEntity);
        dietEntity.setDate(dietDTO.getDate());
        dietEntity.setBreakfast(dietDTO.getBreakfast());
        dietEntity.setLunch(dietDTO.getLunch());
        dietEntity.setDinner(dietDTO.getBreakfast());

        return dietEntity;
    }
}
