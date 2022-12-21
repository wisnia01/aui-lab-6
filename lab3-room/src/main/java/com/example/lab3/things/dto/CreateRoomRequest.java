package com.example.lab3.things.dto;

import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.enitity.Hotel;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateRoomRequest {

    private String name;
    private String number;
    private int area; //in hektars
    private String owner;

    public static Function<CreateRoomRequest, Room> dtoToEntityMapper(Function<String, Hotel> hotelFunction) {
        return request -> Room.builder()
                .name(request.getName())
                .number(request.getNumber())
                .area(request.getArea())
                .owner(hotelFunction.apply(request.getOwner()))
                .build();
    }

}