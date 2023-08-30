package com.techelevator.dao;

import com.techelevator.model.Itinerary;

import java.time.LocalDate;
import java.util.List;

public interface ItineraryDao {
    List<Itinerary> getItineraryListByUsername(String username);

    Itinerary getItineraryById(int id);

    Itinerary getItineraryByDate(String username, LocalDate date);

    Itinerary getNextItineraryForUser(String username, int id);

    void addItinerary();

    void updateItinerary(Itinerary itinerary);

}
