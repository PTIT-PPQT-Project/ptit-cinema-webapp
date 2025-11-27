package com.example.ptitcinema.service;

import com.example.ptitcinema.model.dto.ShowtimeDto;
import java.util.List;

public interface IShowtimeService {
    List<ShowtimeDto> getShowtimesByMovie(int movieId);
}