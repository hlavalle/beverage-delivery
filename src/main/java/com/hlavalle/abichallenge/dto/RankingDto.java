package com.hlavalle.abichallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class RankingDto {

    @Getter @Setter
    @JsonProperty("id_vehicle")
    private Long vehicleId;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private String location;

    @Getter @Setter
    private int capacity;

    @Getter @Setter
    private int score;

}
