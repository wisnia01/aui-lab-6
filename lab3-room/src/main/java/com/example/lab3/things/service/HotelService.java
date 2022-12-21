package com.example.lab3.things.service;

import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class HotelService {
    private HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public Optional<Hotel> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public Hotel create(Hotel hotel) {return repository.save(hotel);}

//    @Transactional
//    public void update(Hotel hotel) {
//        repository.save(hotel);
//    }

    @Transactional
    public void delete(String name) { repository.deleteById(name);}
}
