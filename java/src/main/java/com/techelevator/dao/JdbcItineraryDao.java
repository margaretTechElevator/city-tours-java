package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Must be registered user to store itinerary");
        }
        List<Itinerary> itineraries = new ArrayList<>();

        String sql = "SELECT id, itinerary.user_id, date, start_location, end_location FROM itinerary JOIN users ON users.user_id = itinerary.user_id WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username);

        while (results.next()) {
            Itinerary model = mapRowToItinerary(results);
            List<Integer> landmarkIds = getLandmarkIds(model.getId());
            model.setLandmarks(landmarkIds);
            itineraries.add(model);
        }

        return itineraries;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')") //This is probably unnecessary but since it can access anyone's itinerary it would probably be safest. Frontend can access by date
    public Itinerary getItineraryById(int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid id");
        }
        Itinerary itinerary;

        String sql = "SELECT id, user_id, date, start_location, end_location FROM itinerary WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);

        if (results.next()) {
            itinerary = mapRowToItinerary(results);
            List<Integer> landmarkIds = getLandmarkIds(itinerary.getId());
            itinerary.setLandmarks(landmarkIds);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return itinerary;
    }

    @Override
    public Itinerary getItineraryByDate(String username, LocalDate date) {
        if (date == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid date");
        }
        if (username == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Must be logged in to see itinerary on " + date.toString());
        }
        Itinerary itinerary;

        String sql = "SELECT id, itinerary.user_id, date, start_location, end_location FROM itinerary JOIN users ON users.user_id = itinerary.user_id WHERE username = ? and date = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username,date);

        if (results.next()) {
            itinerary = mapRowToItinerary(results);
            List<Integer> landmarkIds = getLandmarkIds(itinerary.getId());
            itinerary.setLandmarks(landmarkIds);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return itinerary;
    }

    @Override
    public Itinerary getNextItineraryForUser(String username) {

        if (username == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Must be logged in to see itinerary");
        }
        Itinerary itinerary;

        String sql = "SELECT id, itinerary.user_id, date, start_location, end_location FROM itinerary JOIN users ON users.user_id = itinerary.user_id WHERE username = ? AND date >= CURRENT_DATE ORDER BY date ASC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username);

        if (results.next()) {
            itinerary = mapRowToItinerary(results);
            List<Integer> landmarkIds = getLandmarkIds(itinerary.getId());
            itinerary.setLandmarks(landmarkIds);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return itinerary;
    }

    @Override
    public void addItinerary(String username, Itinerary itinerary) {
        try {
            //only 1 itinerary per date
            Itinerary checkExists = getItineraryByDate(username, itinerary.getDate());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only one itinerary allowed per user in one day. Update existing itinerary.");
        } catch (ResponseStatusException e) {
            if (e.getStatus().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        String sql = "INSERT INTO itinerary ( user_id, date, start_location, end_location) VALUES ((SELECT user_id FROM users WHERE username = ?),?,?,?) RETURNING id;";

        try {
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class,
                    username,itinerary.getDate(),itinerary.getStartLocation(),itinerary.getEndLocation());
            if (id != null) {
                itinerary.setId(id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        updateLandmarkList(itinerary.getId(), itinerary.getLandmarks());
    }

    @Override
    public void updateItinerary(Itinerary itinerary) {
        String sql = "UPDATE itinerary SET start_location = ?, end_location = ? WHERE id = ?;";

        try {
            jdbcTemplate.update(sql, itinerary.getStartLocation(), itinerary.getEndLocation(), itinerary.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid update object or parameters");
        }


        updateLandmarkList(itinerary.getId(), itinerary.getLandmarks());
    }

    private void updateLandmarkList(int itineraryId, List<Integer> landmarkList) {
        if(landmarkList == null) {
            //don't change the list if not supplied
            return;
        }

        //check if previous entries exist
        String sqlCheck = "SELECT COUNT(id) FROM itinerary_landmark WHERE itinerary_id = ?;";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, Integer.class, itineraryId);

        if(count > 0) {
            //clear old entries to maintain order
            String sqlClear = "DELETE FROM itinerary_landmark WHERE itinerary_id = ?;";
            jdbcTemplate.update(sqlClear, itineraryId);
        }

        //no further action needed if list is empty
        if(landmarkList.size() == 0) {
            return;
        }

        //add all entries
        //todo consider creating separate DAO for this part of the itinerary to utilize NamedParameterJdbcTemplate;
        // it looks like it could allow a generalized parameter entry to make a single connection call through a map and string building
        String sqlAdd = "INSERT INTO itinerary_landmark (itinerary_id, landmark_id) VALUES (?,?) RETURNING id;";

        for (Integer landmark : landmarkList) {
            jdbcTemplate.queryForObject(sqlAdd, Integer.class, itineraryId ,landmark);
        }
    }

    private List<Integer> getLandmarkIds(int itineraryId) {
        List<Integer> landmarkIds = new ArrayList<>();

        String sql = "SELECT landmark_id FROM itinerary_landmark WHERE itinerary_id = ? ORDER BY id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId);

        while (results.next()) {
            landmarkIds.add(results.getInt("landmark_id"));
        }

        return landmarkIds;
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
