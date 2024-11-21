package com.example.EveryHealth.dto;

import com.example.EveryHealth.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommunityDTO {
    private Long communityId;
    private UserEntity userId;
    private LocalDate date;
    private String communityImageUrl;
    private String content;
}
