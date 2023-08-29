package com.techelevator.dao;

import com.techelevator.model.Landmark;
import java.util.List;

public interface LandmarkDao {
    List<Landmark> getAllLandmarksOfTypeByCity(String city, String type);
    List<Landmark> getAllLandmarksByCity(String city);

    List<Landmark> getAllLandmarksByType(String type);

    Landmark getLandmarkByPlaceId(String placeId);

    boolean addLandmarkToDatabase(Landmark newLandmark);
}
