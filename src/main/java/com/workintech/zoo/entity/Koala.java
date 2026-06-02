package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Koala {

    private Long id;
    private String name;
    private Double weight;
    private Integer sleepHour;
    private String gender;
}