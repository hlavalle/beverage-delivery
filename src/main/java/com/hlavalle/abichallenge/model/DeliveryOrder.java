package com.hlavalle.abichallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "delivery_order")
@NoArgsConstructor
@ToString
public class DeliveryOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private String store;

    @Getter @Setter
    private String location;

    @Getter @Setter
    private int quantity;

}
