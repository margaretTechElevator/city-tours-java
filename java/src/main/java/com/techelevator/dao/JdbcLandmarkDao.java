package com.techelevator.dao;

import com.techelevator.model.Landmark;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarkDao implements LandmarkDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLandmarkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Landmark> getAllLandmarksOfTypeByCity(String city, String type) {
        if (city == null || city.isBlank() || type == null || type.isBlank()) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        List<Landmark> landmarks = new ArrayList<>();

        String sql = "SELECT id, place_id, type, city FROM landmark WHERE city = ? AND type = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,city,type);

        while (results.next()) {
            landmarks.add(mapRowToLandmark(results));
        }

        return landmarks;
    }

    @Override
    public List<Landmark> getAllLandmarksByCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be blank");
        }

        List<Landmark> landmarksInCity = new ArrayList<>();

        String sql = "SELECT id, place_id, type, city FROM landmark WHERE city = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,city);

        while (results.next()) {
            landmarksInCity.add(mapRowToLandmark(results));
        }

        return landmarksInCity;
    }

    @Override
    public List<Landmark> getAllLandmarksByType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("City cannot be blank");
        }

        List<Landmark> landmarksOfType = new ArrayList<>();

        String sql = "SELECT id, place_id, type, city FROM landmark WHERE type = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,type);

        while (results.next()) {
            landmarksOfType.add(mapRowToLandmark(results));
        }

        return landmarksOfType;
    }

    @Override
    public Landmark getLandmarkByPlaceId(String placeId) {
        if (placeId == null || placeId.isBlank()) {
            throw new IllegalArgumentException("Place ID cannot be blank");
        }

        Landmark landmark;

        String sql = "SELECT id, place_id, type, city FROM landmark WHERE place_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,placeId);

        if (results.next()) {
            landmark = mapRowToLandmark(results);
            return landmark;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean addLandmarkToDatabase(Landmark newLandmark) {

        return false;
    }

    private Landmark mapRowToLandmark(SqlRowSet results) {
        int id = results.getInt("id");
        String placeId = results.getString("place_id");
        String type = results.getString("type");
        String city = results.getString("city");
        Landmark landmark = new Landmark(id,placeId,type,city);

        return landmark;
    }
}
