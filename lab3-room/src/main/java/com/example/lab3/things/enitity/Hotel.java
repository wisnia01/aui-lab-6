package com.example.lab3.things.enitity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "owner")
    @ToString.Exclude
    private List<Room> rooms;
}
