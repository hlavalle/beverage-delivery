package com.hlavalle.abichallenge.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "delivery_order")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DeliveryOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    @NonNull
    private String store;

    @Getter @Setter
    @NonNull
    private String location;

    @Getter @Setter
    @NonNull
    private int quantity;

}
