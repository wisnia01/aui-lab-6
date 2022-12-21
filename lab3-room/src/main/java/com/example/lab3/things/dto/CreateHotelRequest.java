package com.example.lab3.things.dto;

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
public class CreateHotelRequest {

    private String name;
    private int age;
    private String phoneNumber;

    public static Function<CreateHotelRequest, Hotel> dtoToEntityMapper() {
        return request -> Hotel.builder()
                .name(request.getName())
                .build();
    }

}