package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Landmark {
    private int id;
    @NotNull
    private String placeId;
    @NotNull
    private String type;
    @NotNull
    private String city;

    public Landmark(int id, String placeId, String type, String city) {
        this.id = id;
        this.placeId = placeId;
        this.type = type;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getType() {
        return type;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
