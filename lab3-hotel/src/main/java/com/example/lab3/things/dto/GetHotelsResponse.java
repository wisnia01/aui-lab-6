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
public class GetHotelsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Hotel {
        private String name;
        private int age;
        private String phoneNumber;
    }

    @Singular
    private List<Hotel> hotels;

    public static Function<Collection<com.example.lab3.things.enitity.Hotel>, GetHotelsResponse> entityToDtoMapper() {
        return hotels -> {
            GetHotelsResponseBuilder response = GetHotelsResponse.builder();
            hotels.stream()
                    .map(hotel -> Hotel.builder()
                            .name(hotel.getName())
                            .age(hotel.getAge())
                            .phoneNumber(hotel.getPhoneNumber())
                            .build())
                    .forEach(response::hotel);
            return response.build();
        };
    }
}
