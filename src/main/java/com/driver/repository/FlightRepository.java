package com.driver.repository;

import com.driver.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FlightRepository {

    private final HashMap<Integer, Flight> flightRepository = new HashMap<>();

    public void save(Flight flight){

        if(flightRepository.containsKey(flight.getFlightId()))
            return;

        flightRepository.put(flight.getFlightId(), flight);
    }

    public HashMap<Integer, Flight> findAll(){
        return flightRepository;
    }

    public Flight findById(Integer flightId){
        if(flightRepository.get(flightId) == null){
            return null;
        }
        return flightRepository.get(flightId);
    }
}
