package com.example.lab3.things.dto;

import com.example.lab3.things.enitity.Room;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateRoomRequest {
    private String name;
    private int area;
    public static BiFunction<Room, UpdateRoomRequest, Room> dtoToEntityUpdater() {
        return (room, request) -> {
            room.setName(request.getName());
            room.setArea(request.getArea());
            return room;
        };
    }
}
