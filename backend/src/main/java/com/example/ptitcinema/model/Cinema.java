package com.example.ptitcinema.model;

public class Cinema {
    private int id;
    private String cinemaName;
    private String location;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCinemaName() { return cinemaName; }
    public void setCinemaName(String cinemaName) { this.cinemaName = cinemaName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}