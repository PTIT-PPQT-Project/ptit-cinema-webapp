package com.example.ptitcinema.model.dto;

import com.example.ptitcinema.model.Cinema;

public class CinemaDto {
    private int id;
    private String name;
    private String location;

    public CinemaDto(Cinema cinema) {
        this.id = cinema.getId();
        this.name = cinema.getCinemaName();
        this.location = cinema.getLocation();
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
}