package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Itinerary;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class ItineraryController {
    private final ItineraryDao dao;
    private final UserDao userDao;

    public ItineraryController(ItineraryDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/itineraries/all", method = RequestMethod.GET)
    public List<Itinerary> getUserItineraryList(Principal user) {
        List<Itinerary> itineraries = dao.getItineraryListByUsername(user.getName());
        return itineraries;
    }

    @RequestMapping(path = "itineraries", method = RequestMethod.GET)
    public Itinerary getItinerary(@RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") LocalDate date, Principal user) {
        if (date == null) {
            String name = user.getName();
            Itinerary itinerary = dao.getNextItineraryForUser(name);

            return itinerary;
        } else {
            Itinerary itinerary = dao.getItineraryByDate(user.getName(), date);

            return itinerary;
        }
    }

    @RequestMapping(path = "/itineraries/{id}", method = RequestMethod.GET)
    public Itinerary getItineraryById(@PathVariable int id) {
        Itinerary itinerary = dao.getItineraryById(id);
        return itinerary;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/itineraries", method = RequestMethod.POST)
    public void addItinerary(@RequestBody Itinerary itinerary, Principal user) {
        dao.addItinerary(user.getName(), itinerary);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/itineraries", method = RequestMethod.PUT)
    public void updateItinerary(@RequestBody Itinerary itinerary, Principal user) {
        //ensure logged in user can access only their itinerary
        int userId = userDao.findIdByUsername(user.getName());
        if (userId != itinerary.getUser()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You may not update another user's itinerary");
        }

        //update
        dao.updateItinerary(itinerary);

    }
}
