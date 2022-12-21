package com.example.lab3.datastore;

import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.enitity.Hotel;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
@Component
public class Datastore {
    private Set<Room> rooms = new HashSet<>();

    private Set<Hotel> hotels = new HashSet<>();

    public synchronized List<Hotel> findAllHotels() {
        return new ArrayList<>(hotels);
    }

    public Optional<Hotel> findHotel(String name) {
        return hotels.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createHotel(Hotel hotel) throws IllegalArgumentException {
        findHotel(hotel.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("This hotel (%s) is already in database", hotel.getName()));
                },
                () -> hotels.add(hotel));
    }

    public synchronized List<Room> findAllRooms() {
        return rooms.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Room> findRoom(String adress) {
        return rooms.stream()
                .filter(room -> room.getNumber().equals(adress))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createRoom(Room room) throws IllegalArgumentException {
        findRoom(room.getNumber()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("This room (%s) is already in database", room.getName()));
                },
                () -> rooms.add(room));
    }

    public synchronized void updateRoom(Room room) throws IllegalArgumentException {
        findRoom(room.getNumber()).ifPresentOrElse(
                original -> {
                    rooms.remove(original);
                    rooms.add(room);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("This room (%s) does not exist", room.getNumber()));
                });
    }

    public synchronized void deleteRoom(String adress) throws IllegalArgumentException {
        findRoom(adress).ifPresentOrElse(
                original -> rooms.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("This room (%s) does not exist", adress));
                });
    }

    public Stream<Room> getRoomStream() {
        return rooms.stream();
    }

}
