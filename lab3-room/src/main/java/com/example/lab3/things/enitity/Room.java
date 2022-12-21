package com.example.lab3.things.enitity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "rooms")
public class Room implements Serializable {
    @Column
    private String name;

    @Id
    private String number;

    @Column
    private int area;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Hotel owner;
}

