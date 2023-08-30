package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.model.Itinerary;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class ItineraryController {
    private final ItineraryDao dao;

    public ItineraryController(ItineraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/itineraries/all", method = RequestMethod.GET)
    public List<Itinerary> getUserItineraryList(Principal user) {
        List<Itinerary> itineraries = dao.getItineraryListByUsername(user.getName());

        return itineraries;
    }

    @RequestMapping(path = "itineraries", method = RequestMethod.GET)
    public Itinerary getItineraryByDate(@RequestParam @DateTimeFormat(pattern = "MMddyyyy") LocalDate date, Principal user) {
        Itinerary itinerary = dao.getItineraryByDate(user.getName(), date);

        return itinerary;
    }

    @RequestMapping(path = "/itineraries/{id}", method = RequestMethod.GET)
    public Itinerary getItineraryById(@PathVariable int id) {
        Itinerary itinerary = dao.getItineraryById(id);

        return itinerary;
    }
}
