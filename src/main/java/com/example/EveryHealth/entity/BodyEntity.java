package com.example.EveryHealth.entity;

import com.example.EveryHealth.dto.BodyDTO;
import com.example.EveryHealth.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "bodies")
public class BodyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id", length = 30, nullable = false)
    private Long bodyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "height")
    private double height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "bmi")
    private double bmi;

    // 골격근량
    @Column(name = "smm")
    private double smm;

    // 체지방률
    @Column(name = "pbf")
    private double pbf;

    public static BodyEntity toBodyEntity(BodyDTO bodyDTO, UserRepository userRepository) {
        BodyEntity bodyEntity = new BodyEntity();
        Long userId = bodyDTO.getUserId();
        UserEntity userEntity = userRepository.findById(userId).get();

        bodyEntity.setUser(userEntity);
        bodyEntity.setDate(bodyDTO.getDate());
        bodyEntity.setHeight(bodyDTO.getHeight());
        bodyEntity.setWeight(bodyDTO.getWeight());
        bodyEntity.setBmi(bodyDTO.getBmi());
        bodyEntity.setSmm(bodyDTO.getSmm());
        bodyEntity.setPbf(bodyDTO.getPbf());


        return bodyEntity;
    }
}
