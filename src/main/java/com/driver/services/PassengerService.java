package com.driver.services;

import com.driver.model.Passenger;
import com.driver.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public void addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }
}
