package com.example.ptitcinema.model.dto;

import com.example.ptitcinema.model.Cinema;
import com.example.ptitcinema.model.Showtime;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class ShowtimeDetailDto {
    private int id;
    private int movieId;
    private int cinemaId;
    private CinemaDto cinema;
    private LocalDate date;
    private String time;
    private BigDecimal price;
    private int roomId;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // Constructor để ánh xạ từ Entity và Cinema Entity
    public ShowtimeDetailDto(Showtime showtime, Cinema cinema) {
        this.id = showtime.getId();
        this.movieId = showtime.getMovieId();
        this.roomId = showtime.getRoomId();
        this.date = showtime.getDate();
        this.time = showtime.getTime().format(TIME_FORMATTER); // Format LocalTime thành String
        this.price = showtime.getPrice();
        
        // Thông tin rạp chiếu
        if (cinema != null) {
            this.cinemaId = cinema.getId();
            this.cinema = new CinemaDto(cinema);
        }
    }

    // Getters
    public int getId() { return id; }
    public int getMovieId() { return movieId; }
    public int getCinemaId() { return cinemaId; }
    public CinemaDto getCinema() { return cinema; }
    public LocalDate getDate() { return date; }
    public String getTime() { return time; }
    public BigDecimal getPrice() { return price; }
    public int getRoomId() { return roomId; }
}