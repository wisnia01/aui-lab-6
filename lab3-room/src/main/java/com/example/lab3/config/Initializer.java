package com.example.lab3.config;

import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.service.RoomService;
import com.example.lab3.things.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private final RoomService roomService;
    private final HotelService hotelService;

    @Autowired
    public Initializer(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @PostConstruct
    private synchronized void init() {
        Hotel jeden = Hotel.builder().name("Super hotel").build();
        Hotel dwa = Hotel.builder().name("HOTEL HOTEL").build();
        Hotel trzy = Hotel.builder().name("hotel 123").build();

        hotelService.create(jeden);
        hotelService.create(dwa);
        hotelService.create(trzy);

        Room one = Room.builder().name("Warszawa").number("100").area(2).owner(jeden).build();
        Room two = Room.builder().name("Gdansk").number("101").area(1).owner(dwa).build();
        Room three = Room.builder().name("Bydgoszcz").number("102").area(3).owner(trzy).build();
        Room four = Room.builder().name("Mlawa").number("103").area(7).owner(jeden).build();
        Room five = Room.builder().name("Wejherowo").number("104").area(4).owner(dwa).build();
        Room six = Room.builder().name("Szczecin").number("105").area(2).owner(trzy).build();

        roomService.create(one);
        roomService.create(two);
        roomService.create(three);
        roomService.create(four);
        roomService.create(five);
        roomService.create(six);

    }
}
