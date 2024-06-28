package com.lamnguyen.server.controllers;


import com.google.gson.Gson;
import com.lamnguyen.server.converter.ConverterEntityToDTO;
import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.models.response.MovieResponseRestApi;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieService;
import com.lamnguyen.server.utils.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/movie/api")
public class MovieRestController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDetailService movieDetailService;
    @Autowired
    private ConverterEntityToDTO converterEntityToDTO;

    @GetMapping(value = "/showtime")
    public APIResponse<List<MovieResponse>> getMovieShow(@RequestParam Map<String, Object> args) {
        String dateStr = (String) args.get("date");
        LocalDateTime date = DateTimeFormat.generateStartDate(dateStr);
        List<MovieResponse> movies = movieService.getMovieHasShowtime(date);
        return APIResponse.<List<MovieResponse>>builder()
                .status(202)
                .message("Success")
                .data(movies)
                .build();
    }

    @GetMapping(value = "/detail/{id}/{date}")
    public APIResponse<MovieDetailResponse> getMovieDetailById(@PathVariable("date") String date, @PathVariable("id") Integer id) {
        MovieDetailResponse result = movieDetailService.getMovieDetail(id, date);
        return APIResponse.<MovieDetailResponse>builder()
                .status(202).message("Success")
                .data(result)
                .build();
    }

    @GetMapping("/")
    public APIResponse<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> movieResponses = movieService.getAllMovies();
        return APIResponse.<List<MovieResponse>>builder()
                .status(202)
                .message("success")
                .data(movieResponses)
                .build();
    }

    @PostMapping("/")
    public APIResponse<MovieResponse> addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.insert(movie);
        newMovie.setMovieReviews(new ArrayList<>());
        MovieResponseRestApi newMovieResponseRestApi = movieService.getMovieResponseRestApi(newMovie.getIdApi());
        MovieResponse newMovieResponse = movieService.convertMovieResponseRestApiToMovieResponse(newMovie, newMovieResponseRestApi);
        return APIResponse.<MovieResponse>builder()
                .status(202)
                .message("success")
                .data(newMovieResponse)
                .build();
    }
}

