package com.example.ptitcinema.controller;

import com.example.ptitcinema.model.dto.ShowtimeDto;
import com.example.ptitcinema.service.IShowtimeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    
    private final IShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(IShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @Operation(summary = "Get Showtimes by Movie", description = "Retrieves grouped showtimes for a specific movie ID.")
    @GetMapping
    public ResponseEntity<List<ShowtimeDto>> getShowtimesByMovie(
            @RequestParam(name = "movieId") Integer movieId) {
        
        // Kiểm tra tham số đầu vào
        if (movieId == null || movieId <= 0) {
            return ResponseEntity.badRequest().build(); 
        }

        List<ShowtimeDto> showtimes = showtimeService.getShowtimesByMovie(movieId);
        
        return ResponseEntity.ok(showtimes);
    }
}