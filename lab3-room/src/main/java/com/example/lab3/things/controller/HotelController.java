package com.example.lab3.things.controller;

import com.example.lab3.things.dto.*;
import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/hotels")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createHotel(@RequestBody CreateHotelRequest request, UriComponentsBuilder builder) {
        Hotel hotel = CreateHotelRequest
                .dtoToEntityMapper()
                .apply(request);
        hotel = hotelService.create(hotel);
        return ResponseEntity.created(builder.pathSegment("api", "hotels", "{id}").buildAndExpand(hotel.getName()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") String id) {
        Optional<Hotel> hotel = hotelService.find(id);
        if (hotel.isPresent()) {
            hotelService.delete(hotel.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
