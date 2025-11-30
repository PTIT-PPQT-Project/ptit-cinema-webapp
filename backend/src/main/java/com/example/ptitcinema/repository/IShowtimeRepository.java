package com.example.ptitcinema.repository;

import com.example.ptitcinema.model.Showtime;
import com.example.ptitcinema.model.Cinema;

import java.util.List;
import java.util.Optional;

public interface IShowtimeRepository {
    Optional<Showtime> findShowtimeById(int showtimeId);
    List<Showtime> findShowtimesByMovieId(int movieId);

    Cinema findCinemaByRoomId(int roomId);
}