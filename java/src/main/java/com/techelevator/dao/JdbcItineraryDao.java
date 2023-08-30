package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcItineraryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Itinerary> getItineraryListByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Must be registered user to store itinerary");
        }
        List<Itinerary> itineraries = new ArrayList<>();

        String sql = "SELECT id, itinerary.user_id, date, start_location, end_location FROM itinerary JOIN users ON users.user_id = itinerary.user_id WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username);

        while (results.next()) {
            itineraries.add(mapRowToItinerary(results));
        }

        return itineraries;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')") //This is probably unnecessary but since it can access anyone's itinerary it would probably be safest. Frontend can access by date
    public Itinerary getItineraryById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("invalid id");
        }
        Itinerary itinerary;

        String sql = "SELECT id, user_id, date, start_location, end_location FROM itinerary WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);

        if (results.next()) {
            itinerary = mapRowToItinerary(results);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return itinerary;
    }

    @Override
    public Itinerary getItineraryByDate(String username, LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (username == null) {
            throw new IllegalArgumentException("Must be logged in to see itinerary on " + date.toString());
        }
        Itinerary itinerary;

        String sql = "SELECT id, itinerary.user_id, date, start_location, end_location FROM itinerary JOIN users ON users.user_id = itinerary.user_id WHERE username = ? and date = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username,date);

        if (results.next()) {
            itinerary = mapRowToItinerary(results);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return itinerary;
    }

    @Override
    public Itinerary getNextItineraryForUser(String username, int id) {
        return null;
    }

    @Override
    public void addItinerary() {

    }

    @Override
    public void updateItinerary(Itinerary itinerary) {

    }

    private Itinerary mapRowToItinerary(SqlRowSet results) {
        int id = results.getInt("id");
        int userId = results.getInt("user_id");
        LocalDate date;
        if (results.getDate("date") != null) {
            date = results.getDate("date").toLocalDate();
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"date storage error");
        }
        String startLocation = results.getString("start_location");
        String endLocation = results.getString("end_location");

        Itinerary itinerary = new Itinerary(id,userId,date,startLocation,endLocation);

        return itinerary;
    }
}
