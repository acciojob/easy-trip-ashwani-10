package com.driver.services;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepository;
import com.driver.repository.FlightRepository;
import com.driver.repository.FlightToPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    FlightToPassengerRepository flightToPassengerRepository;

    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        //find the flight with the respective flight id
        Flight flight = flightRepository.findById(flightId);


        //if flight does not exist with this flightId return null
        if(flight == null){
            return "null";
        }

        //find the city from flight is taking off
        City airportsCity = flight.getFromCity();

        //find the respective airport in that city
        for(String airportName : airportRepository.findAll().keySet()){
            Airport airport = airportRepository.findByName(airportName);
            if(airport.getCity().equals(airportsCity)){
                return airportName;
            }
        }

        return "null";
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int totalRevenue = 0;
        // count the number of passenger booked this flight
        for (Integer passengerId : flightToPassengerRepository.findAll().keySet()){
            if(flightToPassengerRepository.alreadyBooked(passengerId,flightId) != null){
                totalRevenue += 3000 + (passengerId-1)*50;
            }
        }

        return totalRevenue;
    }
}
