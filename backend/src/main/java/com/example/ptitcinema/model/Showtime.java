package com.example.ptitcinema.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;

public class Showtime {
    private int id;
    private int movieId;
    private int roomId;
    private LocalDate date;
    private LocalTime time;
    private BigDecimal price;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}