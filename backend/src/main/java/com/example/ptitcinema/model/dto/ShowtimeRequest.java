package com.example.ptitcinema.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShowtimeRequest {
    private int movieId;
    private int cinemaId; 
    private String date;
    private List<String> times;
    private BigDecimal price;
    private int roomId;

    // Getters and Setters
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public int getCinemaId() { return cinemaId; }
    public void setCinemaId(int cinemaId) { this.cinemaId = cinemaId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public List<String> getTimes() { return times; }
    public void setTimes(List<String> times) { this.times = times; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
}