package com.example.EveryHealth.dto;

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
}
