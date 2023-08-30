package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Itinerary {
    private int id;
    private int user;
    @NotNull
    private LocalDate date;
    @NotNull
    private String startLocation;
    @NotNull
    private String endLocation;

    public Itinerary(int id, int user, LocalDate date, String startLocation, String endLocation) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
}
