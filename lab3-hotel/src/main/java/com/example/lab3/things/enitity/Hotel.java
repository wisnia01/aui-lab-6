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
@Table(name = "hotels")
public class Hotel implements Serializable {
    @Id
    private String name;

    @Column
    private int age;

    @Column(unique = true)
    private String phoneNumber;
}
