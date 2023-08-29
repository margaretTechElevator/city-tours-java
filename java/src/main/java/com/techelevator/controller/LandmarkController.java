package com.techelevator.controller;

import com.techelevator.dao.LandmarkDao;
import com.techelevator.model.Landmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class LandmarkController {
    private final LandmarkDao dao;

    public LandmarkController(LandmarkDao dao) {
        this.dao = dao;
    }

    //todo update with alternate routes other than city
    @RequestMapping(path = "/landmarks")
    public List<Landmark> getLandmarks(@RequestParam(value = "city", required = false) String city) {
        

        try {
            List<Landmark> landmarks = dao.getAllLandmarksByCity(city);
            return landmarks;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getLocalizedMessage());
        }
    }
}
