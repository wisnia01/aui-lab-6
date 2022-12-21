package com.example.lab3.things.repository;

import com.example.lab3.things.enitity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

}
