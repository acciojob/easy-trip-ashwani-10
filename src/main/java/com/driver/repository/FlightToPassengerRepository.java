package com.driver.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FlightToPassengerRepository {

    private final HashMap<Integer, List<Integer>> flightTopassenger = new HashMap<>();

    public void save(Integer passengerId,Integer flightId){
        if(flightTopassenger.get(passengerId) == null){
            flightTopassenger.put(passengerId,new ArrayList<>());
        }
        List<Integer> PassengersFlightLists = flightTopassenger.get(passengerId);
        PassengersFlightLists.add(flightId);
    }

    public Integer alreadyBooked(Integer passengerId, Integer flightId){
        List<Integer> PassengersFlightLists = flightTopassenger.get(passengerId);
        if(PassengersFlightLists != null) {
            for (Integer flight : PassengersFlightLists) {
                if (flight.equals(flightId))
                    return flightId;
            }
        }
        return null;
    }
    public List<Integer> getPassengersFlights(Integer passengerId){
        return flightTopassenger.get(passengerId);
    }
    public HashMap<Integer, List<Integer>> findAll(){
        return flightTopassenger;
    }

    public void cancelTicket(Integer passengerId,Integer flightId){
        if(flightTopassenger.get(passengerId) != null) {
            List<Integer> PassengersFlightLists = flightTopassenger.get(passengerId);
            PassengersFlightLists.remove(flightId);
        }
    }

}
