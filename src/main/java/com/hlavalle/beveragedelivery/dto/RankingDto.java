package com.hlavalle.beveragedelivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
