package com.example.ptitcinema.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ShowtimeDto {
    private int id; // ID của suất chiếu đầu tiên trong nhóm
    private int movieId;
    private int cinemaId;
    private CinemaDto cinema;
    private LocalDate date;
    private List<String> times; // Danh sách các giờ chiếu trong ngày đó
    private BigDecimal price;
    private int roomId; // ID của phòng chiếu (giả sử tất cả times trong 1 nhóm dùng chung 1 phòng/giá)
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public int getCinemaId() { return cinemaId; }
    public void setCinemaId(int cinemaId) { this.cinemaId = cinemaId; }

    public CinemaDto getCinema() { return cinema; }
    public void setCinema(CinemaDto cinema) { this.cinema = cinema; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public List<String> getTimes() { return times; }
    public void setTimes(List<String> times) { 
        // Sắp xếp giờ chiếu trước khi set
        this.times = times.stream().sorted().collect(Collectors.toList()); 
    }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
}