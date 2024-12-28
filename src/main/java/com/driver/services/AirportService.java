package com.driver.services;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepository;
import com.driver.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;

    @Autowired
    FlightRepository flightRepository;

    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    public String getLargestAirportName() {
        List<String> airportList = new ArrayList<>();
        int maxTerminals = -1;
        for (String airportName : airportRepository.findAll().keySet()){
            Airport airport = airportRepository.findByName(airportName);
            if(airport.getNoOfTerminals() > maxTerminals)
                maxTerminals = airport.getNoOfTerminals();
        }
        for (String airportName : airportRepository.findAll().keySet()){
            Airport airport = airportRepository.findByName(airportName);
            if(airport.getNoOfTerminals() == maxTerminals)
                airportList.add(airportName);
        }
        if(airportList.isEmpty())
            return "null";

        Collections.sort(airportList);
        return airportList.get(0);
    }


    public int getNumberOfPeopleOnAirport(Date date, String airportName) {
        //find airport with the airports name
        Airport airport = airportRepository.findByName(airportName);
        //find airports city
        City airportsCity = airport.getCity();
        //calculate total Number Of People On Airport on the given date
        int totalNumberOfPeopleOnAirport = 0;
            for(Integer flightId : flightRepository.findAll().keySet()){
                Flight flight = flightRepository.findById(flightId);

                if(flight.getToCity() == airportsCity && flight.getFlightDate() == date){
                    totalNumberOfPeopleOnAirport++;
                }
                if(flight.getFromCity() == airportsCity && flight.getFlightDate() == date){
                    totalNumberOfPeopleOnAirport++;
                }

            }

        return totalNumberOfPeopleOnAirport;
    }
}
