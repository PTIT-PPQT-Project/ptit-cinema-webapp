package com.example.ptitcinema.repository.impl;

import com.example.ptitcinema.model.Showtime;
import com.example.ptitcinema.model.Cinema;
import com.example.ptitcinema.repository.IShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShowtimeRepository implements IShowtimeRepository {
    private final JdbcTemplate sqlJdbcTemplate;

    @Autowired
    public ShowtimeRepository(final JdbcTemplate sqlJdbcTemplate) {
        this.sqlJdbcTemplate = sqlJdbcTemplate;
    }

    private final RowMapper<Showtime> showtimeRowMapper = (rs, rowNum) -> {
        Showtime st = new Showtime();
        st.setId(rs.getInt("Id"));
        st.setMovieId(rs.getInt("MovieId"));
        st.setRoomId(rs.getInt("RoomId"));
        st.setDate(rs.getObject("Date", java.time.LocalDate.class));
        st.setTime(rs.getObject("Time", java.time.LocalTime.class));
        st.setPrice(rs.getBigDecimal("Price"));
        return st;
    };

    private final RowMapper<Cinema> cinemaRowMapper = (rs, rowNum) -> {
        Cinema c = new Cinema();
        c.setId(rs.getInt("Id"));
        c.setCinemaName(rs.getString("CinemaName"));
        c.setLocation(rs.getString("Location"));
        return c;
    };

    @Override
    public List<Showtime> findShowtimesByMovieId(int movieId) {
        String sqlQuery = "SELECT * FROM Showtime WHERE MovieId = ? ORDER BY Date, Time";
        return sqlJdbcTemplate.query(sqlQuery, showtimeRowMapper, movieId);
    }

    @Override
    public Cinema findCinemaByRoomId(int roomId) {
        String sqlQuery = "SELECT c.Id, c.CinemaName, c.Location " +
                          "FROM Cinema c " +
                          "JOIN CinemaRoom cr ON c.Id = cr.CinemaId " +
                          "WHERE cr.Id = ?";
        try {
            return sqlJdbcTemplate.queryForObject(sqlQuery, cinemaRowMapper, roomId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Showtime> findShowtimeById(int showtimeId) {
        String sqlQuery = "SELECT * FROM Showtime WHERE Id = ?";
        try {
            Showtime showtime = sqlJdbcTemplate.queryForObject(sqlQuery, showtimeRowMapper, showtimeId);
            return Optional.ofNullable(showtime);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}