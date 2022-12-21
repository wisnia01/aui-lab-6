package com.example.lab3.things.controller;

import com.example.lab3.things.dto.CreateRoomRequest;
import com.example.lab3.things.dto.GetRoomResponse;
import com.example.lab3.things.dto.GetRoomsResponse;
import com.example.lab3.things.dto.UpdateRoomRequest;
import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.service.RoomService;
import com.example.lab3.things.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/rooms")
public class RoomController {
    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetRoomResponse> getRoom(@PathVariable("id") String id) {
        Optional<Room> room = roomService.find(id);
        return room.map(value -> ResponseEntity.ok(
                GetRoomResponse
                        .entityToDtoMapper()
                        .apply(value))
                )
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetRoomsResponse> getRooms() {
        return ResponseEntity.ok(
                GetRoomsResponse
                        .entityToDtoMapper()
                        .apply(roomService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<Void> createRoom(@RequestBody CreateRoomRequest request, UriComponentsBuilder builder) {
        Room room = CreateRoomRequest
                .dtoToEntityMapper(name -> hotelService.find(name).orElseThrow())
                .apply(request);
        room = roomService.create(room);
        return ResponseEntity.created(builder.pathSegment("api", "rooms", "{id}").buildAndExpand(room.getNumber()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateRoom(@RequestBody UpdateRoomRequest request, @PathVariable("id") String id) {
        Optional<Room> room = roomService.find(id);
        if (room.isPresent()) {
            UpdateRoomRequest.dtoToEntityUpdater().apply(room.get(), request);
            roomService.update(room.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") String id) {
        Optional<Room> room = roomService.find(id);
        if (room.isPresent()) {
            roomService.delete(room.get().getNumber());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
