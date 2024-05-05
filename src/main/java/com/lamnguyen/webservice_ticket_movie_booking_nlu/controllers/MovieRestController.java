package com.lamnguyen.webservice_ticket_movie_booking_nlu.controllers;


import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieDetailResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/movie/api")
public class MovieRestController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movie-showtime")
    public List<MovieResponse> getMovieShow(@RequestParam Map<String, Object> args) {
        String dateStr = (String) args.get("date");
        if (dateStr != null) {
            LocalDate date = LocalDate.parse(dateStr);
            return movieService.getMovieHasShowtime(date);
        }
        return movieService.getMovieHasShowtime(LocalDate.now());
    }

    @GetMapping(value = "/detail/{id}")
    public MovieDetailResponse getMovieDetailById(@PathVariable("id") Integer id){
        return movieService.getMovieDetailById(id);
    }
}

