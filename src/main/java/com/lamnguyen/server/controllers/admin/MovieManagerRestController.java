package com.lamnguyen.server.controllers.admin;


import com.lamnguyen.server.converter.ConverterEntityToDTO;
import com.lamnguyen.server.exceptions.ApplicationException;
import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.models.response.MovieResponseRestApi;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/movie/api")
public class MovieManagerRestController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public APIResponse<List<MovieDetailResponse>> getAllMovies() {
        List<MovieDetailResponse> movies = movieService.getAllMovies();
        return APIResponse.<List<MovieDetailResponse>>builder()
                .status(202)
                .message("success")
                .data(movies)
                .build();
    }

    @PostMapping("/add")
    public APIResponse<MovieResponse> addMovie(@RequestBody Movie movie) {
        MovieResponse newMovieResponse = movieService.addMovie(movie);
        return APIResponse.<MovieResponse>builder()
                .status(202)
                .message("success")
                .data(newMovieResponse)
                .build();
    }
}

