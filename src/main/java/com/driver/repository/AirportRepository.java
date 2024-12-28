package com.driver.repository;

import com.driver.model.Airport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AirportRepository {
    private final HashMap<String, Airport> airportRepository = new HashMap<>();

    public void save(Airport airport){
        airportRepository.put(airport.getAirportName(), airport);
    }

    public HashMap<String, Airport> findAll(){
        return airportRepository;
    }

    public Airport findByName(String airportName){
        return airportRepository.get(airportName);
    }
}
