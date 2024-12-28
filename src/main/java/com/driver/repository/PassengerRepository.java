package com.driver.repository;

import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PassengerRepository {

    private final HashMap<Integer, Passenger> passengerRepository = new HashMap<>();

    public void save(Passenger passenger){
        if(passengerRepository.containsKey(passenger.getPassengerId())){
            return;
        }
        passengerRepository.put(passenger.getPassengerId(),passenger);
    }
}
