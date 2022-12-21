package com.example.lab3.things.event;

import com.example.lab3.things.enitity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class HotelEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public HotelEventRepository(@Value("${rooms.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Hotel hotel) {
        restTemplate.delete("/hotels/{name}", hotel.getName());
    }

    public void create(Hotel hotel) {
        restTemplate.postForLocation("/hotels", PostHotelRequest.entityToDtoMapper().apply(hotel));
    }
}
