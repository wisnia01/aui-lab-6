package com.example.lab3.config;

import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private final HotelService hotelService;

    @Autowired
    public Initializer(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostConstruct
    private synchronized void init() {
        Hotel jeden = Hotel.builder().name("HOTEL_HOTEL").age(1).phoneNumber("3784565748").build();
        Hotel dwa = Hotel.builder().name("hotel_123").age(2222).phoneNumber("28374447328").build();
        Hotel trzy = Hotel.builder().name("super_HOTEL").age(5656).phoneNumber("666666666").build();

        hotelService.create(jeden);
        hotelService.create(dwa);
        hotelService.create(trzy);
    }
}
