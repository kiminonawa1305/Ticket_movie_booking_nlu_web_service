package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.services.MovieFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movie-favourite/api")
public class MovieFavoriteController {
    @Autowired
    private MovieFavoriteService movieFavoriteService;

    @GetMapping("/{userId}")
    public APIResponse<List<MovieResponse>> getListFavoriteMoviesDetailByUserId(@PathVariable("userId") Integer userId) {
        List<MovieResponse> favorites = movieFavoriteService.getFavoriteMoviesByUserId(userId);

        return APIResponse.<List<MovieResponse>>builder()
                .status(202)
                .message("success")
                .data(favorites)
                .build();
    }
}