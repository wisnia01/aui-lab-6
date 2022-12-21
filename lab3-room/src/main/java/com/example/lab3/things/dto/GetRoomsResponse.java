package com.example.lab3.things.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetRoomsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Room {
        private String number;
        private String name;

    }

    @Singular
    private List<Room> rooms;

    public static Function<Collection<com.example.lab3.things.enitity.Room>, GetRoomsResponse> entityToDtoMapper() {
        return rooms -> {
            GetRoomsResponseBuilder response = GetRoomsResponse.builder();
            rooms.stream()
                    .map(room -> Room.builder()
                            .number(room.getNumber())
                            .name(room.getName())
                            .build())
                    .forEach(response::room);
            return response.build();
        };
    }
}
