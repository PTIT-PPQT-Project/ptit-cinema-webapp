package com.example.ptitcinema.service.impl;

import com.example.ptitcinema.model.Showtime;
import com.example.ptitcinema.model.Cinema;
import com.example.ptitcinema.model.dto.CinemaDto;
import com.example.ptitcinema.model.dto.ShowtimeDetailDto;
import com.example.ptitcinema.model.dto.ShowtimeDto;
import com.example.ptitcinema.repository.IShowtimeRepository;
import com.example.ptitcinema.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
public class ShowtimeService implements IShowtimeService {
    private final IShowtimeRepository showtimeRepository;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    public ShowtimeService(IShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Override
    public List<ShowtimeDto> getShowtimesByMovie(int movieId) {
        List<Showtime> allShowtimes = showtimeRepository.findShowtimesByMovieId(movieId);

        if (allShowtimes.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, List<Showtime>> groupedShowtimes = allShowtimes.stream()
                .collect(Collectors.groupingBy(
                        showtime -> showtime.getDate().toString() + "_" + 
                                    showtime.getRoomId() + "_" + 
                                    showtime.getPrice().toString()
                ));

        List<ShowtimeDto> result = new ArrayList<>();
        
        Map<Integer, CinemaDto> cinemaCache = new HashMap<>(); 

        for (List<Showtime> group : groupedShowtimes.values()) {
            Showtime firstShowtime = group.get(0);
            
            CinemaDto cinemaDto = cinemaCache.computeIfAbsent(firstShowtime.getRoomId(), roomId -> {
                Cinema cinema = showtimeRepository.findCinemaByRoomId(roomId);
                return cinema != null ? new CinemaDto(cinema) : null; 
            });

            if (cinemaDto == null) continue; 

            List<String> times = group.stream()
                    .map(st -> st.getTime().format(timeFormatter))
                    .collect(Collectors.toList());

            ShowtimeDto dto = new ShowtimeDto();
            dto.setId(firstShowtime.getId());
            dto.setMovieId(firstShowtime.getMovieId());
            dto.setCinemaId(cinemaDto.getId());
            dto.setCinema(cinemaDto);
            dto.setDate(firstShowtime.getDate());
            dto.setTimes(times);
            dto.setPrice(firstShowtime.getPrice());
            dto.setRoomId(firstShowtime.getRoomId());
            
            result.add(dto);
        }

        return result;
    }
    
    @Override
    public Optional<ShowtimeDetailDto> getShowtimeDetail(int showtimeId) {
        // 1. Lấy thông tin Showtime
        Optional<Showtime> showtimeOptional = showtimeRepository.findShowtimeById(showtimeId);
        
        if (showtimeOptional.isEmpty()) {
            return Optional.empty();
        }

        Showtime showtime = showtimeOptional.get();
        
        // 2. Lấy thông tin Cinema (Rạp chiếu) dựa trên RoomId
        Cinema cinema = showtimeRepository.findCinemaByRoomId(showtime.getRoomId());
        
        // 3. Chuyển đổi sang DTO chi tiết
        ShowtimeDetailDto dto = new ShowtimeDetailDto(showtime, cinema);
        
        return Optional.of(dto);
    }
}