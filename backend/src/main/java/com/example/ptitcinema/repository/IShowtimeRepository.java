package com.example.ptitcinema.repository;

import com.example.ptitcinema.model.Showtime;
import com.example.ptitcinema.model.Cinema;

import java.util.List;

public interface IShowtimeRepository {
    // Lấy tất cả suất chiếu (Showtime) cho một Movie ID
    List<Showtime> findShowtimesByMovieId(int movieId);

    // Lấy thông tin rạp (Cinema) dựa trên Room ID
    Cinema findCinemaByRoomId(int roomId);
}