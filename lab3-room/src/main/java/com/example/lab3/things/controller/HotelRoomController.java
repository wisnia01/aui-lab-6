package com.example.lab3.things.controller;

import com.example.lab3.things.dto.CreateRoomRequest;
import com.example.lab3.things.dto.GetRoomResponse;
import com.example.lab3.things.dto.GetRoomsResponse;
import com.example.lab3.things.dto.UpdateRoomRequest;
import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.service.RoomService;
import com.example.lab3.things.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/hotels/{hotel}/rooms")
public class HotelRoomController {
    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public HotelRoomController(RoomService roomService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<GetRoomsResponse> getRooms(@PathVariable("hotel") String name) {
        Optional<Hotel> hotel = hotelService.find(name);
        return hotel.map(value -> ResponseEntity.ok(GetRoomsResponse.entityToDtoMapper().apply(roomService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetRoomResponse> getRoom(@PathVariable("hotel") String name,
                                                   @PathVariable("id") String id) {
        return roomService.find(name, id)
                .map(value -> ResponseEntity.ok(GetRoomResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postRoom(@PathVariable("hotel") String name,
                                         @RequestBody CreateRoomRequest request,
                                         UriComponentsBuilder builder) {
        Optional<Hotel> owner = hotelService.find(name);
        if (owner.isPresent()) {
            Room room = CreateRoomRequest.dtoToEntityMapper(n -> hotelService.find(n).orElseThrow()).apply(request);
            room = roomService.create(room);
            return ResponseEntity.created(builder.pathSegment("api","hotels","{hotel}","rooms","{id}")
                    .buildAndExpand(owner.get().getName(), room.getNumber()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("hotel") String name,
                                           @PathVariable("id") String number) {
        Optional<Room> room = roomService.find(name, number);
        if (room.isPresent()) {
            roomService.delete(room.get().getNumber());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putRoom(@PathVariable("hotel") String name,
                                        @PathVariable("id") String number,
                                        @RequestBody UpdateRoomRequest request) {
        Optional<Room> room = roomService.find(name, number);
        if (room.isPresent()) {
            UpdateRoomRequest.dtoToEntityUpdater().apply(room.get(), request);
            roomService.update(room.get());
            return  ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
