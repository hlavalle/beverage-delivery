package com.hlavalle.abichallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@ToString
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
