package com.example.lab3.things.dto;

import com.example.lab3.things.enitity.Room;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetRoomResponse {

    private String name;
    private String number;
    private int area;
    private String owner;

    public static Function<Room, GetRoomResponse> entityToDtoMapper() {
        return room -> GetRoomResponse.builder()
                .name(room.getName())
                .number(room.getNumber())
                .area(room.getArea())
                .owner(room.getOwner().getName())
                .build();
    }
}
