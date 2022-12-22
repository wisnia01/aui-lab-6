package com.example.lab3.things.service;

import com.example.lab3.things.enitity.Hotel;
import com.example.lab3.things.event.HotelEventRepository;
import com.example.lab3.things.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private HotelRepository repository;
    private HotelEventRepository eventRepository;

    @Autowired
    public HotelService(HotelRepository repository, HotelEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Hotel> find(String name) {
        return repository.findById(name);
    }

    public List<Hotel> findAll() {return repository.findAll();}
    @Transactional
    public Hotel create(Hotel hotel) {
        Hotel tmp = repository.save(hotel);
        eventRepository.create(hotel);
        return tmp;}

    @Transactional
    public void update(Hotel hotel) {
        repository.save(hotel);
    }

    @Transactional
    public void delete(String name) {
        Hotel hotel = repository.findById(name).get();
        repository.deleteById(name);
        eventRepository.delete(hotel);
    }
//    @Transactional
//    public void updatePortrait(String id, InputStream is) {
//        repository.findById(id).ifPresent(hotel -> {
//            try {
//                hotel.setPortrait(is.readAllBytes());
////                repository.update(character);
//            } catch (IOException ex) {
//                throw new IllegalStateException(ex);
//            }
//        });
//    }

}
