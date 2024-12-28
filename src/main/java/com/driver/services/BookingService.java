package com.driver.services;

import com.driver.model.Flight;
import com.driver.repository.FlightRepository;
import com.driver.repository.FlightToPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    FlightToPassengerRepository flightToPassengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public String bookTicket(Integer flightId, Integer passengerId) {
        Flight flight = flightRepository.findById(flightId);

        //if passenger already booked this flight
        if(flightToPassengerRepository.alreadyBooked(passengerId,flightId) != null && flightToPassengerRepository.alreadyBooked(passengerId,flightId).equals(flightId)){
            return "You already booked this flight";
        }

        // count the number of passenger booked this flight
        int passengersAlreadyBookedThisFlight = 0;
            for (Integer passenger : flightToPassengerRepository.findAll().keySet()) {
                List<Integer> passengersFlightsList = flightToPassengerRepository.getPassengersFlights(passenger);
                for(Integer passengersThisFlight : passengersFlightsList){
                    if(passengersThisFlight.equals(flightId)){
                        passengersAlreadyBookedThisFlight++;
                    }
                }
            }

        // check max capacity of the flight is > passengers already booked this flight
        if (passengersAlreadyBookedThisFlight >= flight.getMaxCapacity()){
            return "No Tickets Available";
        }

        if(flightToPassengerRepository.alreadyBooked(passengerId,flightId) == null){
            flightToPassengerRepository.save(passengerId,flightId);
            return "SUCCESS";
        }

        return "FAILURE";
    }

    public String cancelTicket(Integer passengerId,Integer flightId) {

        //if no flight available with this flightId
        if(flightRepository.findById(flightId) == null){
            return "No flights available with this flightId";
        }

        //if passenger not booked this flight
        if(flightToPassengerRepository.alreadyBooked(passengerId,flightId) == null){
            return "You have not booked this flight";
        }

        //cancel the booked ticket by respective passenger
        flightToPassengerRepository.cancelTicket(passengerId,flightId);

        return "SUCCESS";
    }

    public int calculateFlightFare(Integer flightId) {
        int passengersAlreadyBooked = 0;

        for (Integer passenger : flightToPassengerRepository.findAll().keySet()){
            if(flightToPassengerRepository.alreadyBooked(passenger,flightId).equals(flightId)){
                passengersAlreadyBooked++;
            }
        }

        return 3000 + passengersAlreadyBooked*50;
    }

    public int countOfBookings(Integer passengerId) {
        int countOfBookings = 0;

        for(Integer passengersId : flightToPassengerRepository.findAll().keySet()){
            if(passengersId.equals(passengerId)){
                countOfBookings++;
            }
        }
        return countOfBookings;
    }
}
