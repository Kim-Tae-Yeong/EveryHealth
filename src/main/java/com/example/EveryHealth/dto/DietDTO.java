package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.DietEntity;
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
public class DietDTO {
    private Long dietId;
    private Long userId;
    private LocalDate date;
    private String breakfast;
    private String lunch;
    private String dinner;

    public static DietDTO toDietDTO(DietEntity dietEntity, UserRepository userRepository) {
        DietDTO dietDTO = new DietDTO();

        UserEntity userEntity = userRepository.findById(dietEntity.getUser().getUserId()).get();
        Long userId = userEntity.getUserId();

        dietDTO.setDietId(dietEntity.getDietId());
        dietDTO.setUserId(userId);
        dietDTO.setDate(dietEntity.getDate());
        dietDTO.setBreakfast(dietEntity.getBreakfast());
        dietDTO.setLunch(dietEntity.getLunch());
        dietDTO.setDinner(dietEntity.getDinner());

        return dietDTO;
    }
}
