package com.example.lab3.things.event;

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
public class PostHotelRequest {

    /**
     * User's login.
     */
    private String name;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Hotel, PostHotelRequest> entityToDtoMapper() {
        return entity -> PostHotelRequest.builder()
                .name(entity.getName())
                .build();
    }

}

