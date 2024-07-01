package com.lamnguyen.server.controllers;


import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieService;
import com.lamnguyen.server.utils.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/movie/api")
public class MovieRestController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDetailService movieDetailService;

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

    @GetMapping(value = "/detail/{userId}/{id}/{date}")
    public APIResponse<MovieDetailResponse> getMovieDetailById(@PathVariable("date") String date, @PathVariable("userId") Integer userId, @PathVariable("id") Integer id) {
        MovieDetailResponse result = movieDetailService.getMovieDetail(userId, id, date);
        return APIResponse.<MovieDetailResponse>builder()
                .status(202).message("Success")
                .data(result)
                .build();
    }
}

