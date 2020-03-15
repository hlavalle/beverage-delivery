package com.hlavalle.beveragedelivery.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private String location;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private int capacity;

}
