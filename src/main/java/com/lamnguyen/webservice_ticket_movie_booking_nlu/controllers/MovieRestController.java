package com.lamnguyen.webservice_ticket_movie_booking_nlu.controllers;


import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.APIResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieDetailResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/movie/api")
public class MovieRestController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/showtime")
    public APIResponse<List<MovieResponse>> getMovieShow(@RequestParam Map<String, Object> args) {
        String dateStr = (String) args.get("date");
        LocalDate date;
        if (dateStr != null) date = LocalDate.parse(dateStr);
        else date = LocalDate.now();
        List<MovieResponse> movies = movieService.getMovieHasShowtime(date);
        return APIResponse.<List<MovieResponse>>builder()
                .status(202)
                .message("Success")
                .data(movies)
                .build();
    }

    @GetMapping(value = "/detail/{id}")
    public APIResponse<MovieDetailResponse> getMovieDetailById(@PathVariable("id") Integer id) {
        return APIResponse.<MovieDetailResponse>builder()
                .status(202).message("Success")
                .data(movieService.getMovieDetailById(id))
                .build();
    }
}

