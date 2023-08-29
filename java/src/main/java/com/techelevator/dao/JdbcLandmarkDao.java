package com.techelevator.dao;

import com.techelevator.model.Landmark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarkDao implements LandmarkDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLandmarkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Landmark> getAllLandmarksByCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null");
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
        return null;
    }

    @Override
    public Landmark getLandmarkByPlaceId(String placeId) {
        return null;
    }

    @Override
    public boolean addLandmarkToDatabase() {
        return false;
    }

    //todo implement
    private Landmark mapRowToLandmark(SqlRowSet results) {
        int id = results.getInt("id");
        String placeId = results.getString("place_id");
        String type = results.getString("type");
        String city = results.getString("city");
        Landmark landmark = new Landmark(id,placeId,type,city);

        return landmark;
    }
}
