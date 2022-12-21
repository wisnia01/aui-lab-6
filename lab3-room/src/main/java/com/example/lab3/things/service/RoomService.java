package com.example.lab3.things.service;

import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.repository.RoomRepository;
import com.example.lab3.things.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public Optional<Room> find(String number) {
        return roomRepository.findById(number);
    }

    public Optional<Room> find(String name, String number) {
        Optional<Hotel> hotel = hotelRepository.findById(name);
        if (hotel.isPresent()) {
            return roomRepository.findByNumberAndOwner(number, hotel.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> findAll(Hotel owner) {
        return roomRepository.findAllByOwner(owner);
    }

    @Transactional
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    public void update(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public void delete(String number) {
        roomRepository.deleteById(number);
    }

}
