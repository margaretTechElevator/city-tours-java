package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.model.Itinerary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @RequestMapping(path = "/itineraries", method = RequestMethod.GET)
    public List<Itinerary> getUserItineraryList(Principal user) {
        List<Itinerary> itineraries = dao.getItineraryListByUsername(user.getName());

        return itineraries;
    }
}
