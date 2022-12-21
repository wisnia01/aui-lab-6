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
public class GetHotelResponse {

    private String name;
    private int age;
    private String phoneNumber;

    public static Function<Hotel, GetHotelResponse> entityToDtoMapper() {
        return hotel -> GetHotelResponse.builder()
                .name(hotel.getName())
                .age(hotel.getAge())
                .phoneNumber(hotel.getPhoneNumber())
                .build();
    }
}
