package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.BodyEntity;
import com.example.EveryHealth.entity.UserEntity;
import com.example.EveryHealth.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BodyDTO {
    private Long bodyId;
    private Long userId;
    private LocalDate date;
    private Integer height;
    private Integer weight;
    private Integer bmi;
    private Integer smm;
    private Integer pbf;

    public static BodyDTO toBodyDTO(BodyEntity bodyEntity, UserRepository userRepository) {
        BodyDTO bodyDTO = new BodyDTO();

        UserEntity userEntity = userRepository.findById(bodyEntity.getUser().getUserId()).get();
        Long userId = userEntity.getUserId();

        bodyDTO.setBodyId(bodyEntity.getBodyId());
        bodyDTO.setUserId(userId);
        bodyDTO.setDate(bodyEntity.getDate());
        bodyDTO.setHeight(bodyEntity.getHeight());
        bodyDTO.setWeight(bodyEntity.getWeight());
        bodyDTO.setBmi(bodyEntity.getBmi());
        bodyDTO.setSmm(bodyEntity.getSmm());
        bodyDTO.setPbf(bodyEntity.getPbf());

        return bodyDTO;
    }
}
