package com.example.lab3.things.dto;

import com.example.lab3.things.enitity.Hotel;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateHotelRequest {
    private int age;
    private String phoneNumber;
    public static BiFunction<Hotel, UpdateHotelRequest, Hotel> dtoToEntityUpdater() {
        return (hotel, request) -> {
            hotel.setAge(request.getAge());
            hotel.setPhoneNumber(request.getPhoneNumber());
            return hotel;
        };
    }
}