package com.techelevator.controller;

import com.techelevator.dao.LandmarkDao;
import com.techelevator.model.Landmark;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class LandmarkController {
    private final LandmarkDao dao;

    public LandmarkController(LandmarkDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/landmarks", method = RequestMethod.GET)
    public List<Landmark> getLandmarks(@RequestParam(value = "city", required = false) String city,
                                       @RequestParam(value = "type", required = false) String type) {
        if ((city == null || city.isBlank()) && (type == null || type.isBlank())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Required arguments: City or Type");

        } else if (city == null || city.isBlank()) {
            try {
                List<Landmark> landmarks = dao.getAllLandmarksByType(type);
                return landmarks;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getLocalizedMessage());
            }
        } else if (type == null || type.isBlank()) {
            try {
                List<Landmark> landmarks = dao.getAllLandmarksByCity(city);
                return landmarks;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getLocalizedMessage());
            }
        }
        List<Landmark> landmarks = dao.getAllLandmarksOfTypeByCity(city,type);
        return landmarks;
    }

    @RequestMapping(path = "landmarks/{placeId}", method = RequestMethod.GET)
    public Landmark getLandmarkByPlaceId(@PathVariable String placeId) {
        Landmark landmark = dao.getLandmarkByPlaceId(placeId);

        return landmark;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "landmarks", method = RequestMethod.POST)
    public void addLandmarkToDatabase(@RequestBody Landmark landmark) {
        dao.addLandmarkToDatabase(landmark);
    }
}
