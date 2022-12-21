package com.example.lab3.things.repository;

import com.example.lab3.things.enitity.Room;
import com.example.lab3.things.enitity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findAllByOwner(Hotel owner);
    Optional<Room> findByNumberAndOwner(String number, Hotel owner);
}
