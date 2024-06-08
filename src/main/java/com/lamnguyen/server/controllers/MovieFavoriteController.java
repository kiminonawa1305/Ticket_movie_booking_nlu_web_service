package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.entity.MovieFavorite;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MovieFavoriteController {
    @Autowired
    private MovieFavoriteService movieFavoriteService;

    @Autowired
    private MovieDetailService movieDetailService;

    List<APIResponse<MovieDetailResponse>> result = new ArrayList<>();

    @GetMapping("/customers/{customerId}/favorites")
    public APIResponse<MovieDetailResponse> getFavoriteMoviesDetailByCustomerId(@PathVariable("customerId") Integer customerId) {
//        List<Integer> favorites = movieFavoriteService.getFavoriteMoviesByCustomerId(customerId);
        MovieDetailResponse result = movieDetailService.getMovieDetail(customerId);
        return APIResponse.<MovieDetailResponse>builder()
                .status(202).message("Success")
                .data(result)
                .build();
    }

    @GetMapping("/customers/favoriteList/{customerId}")
    public List<APIResponse<MovieDetailResponse>> getListFavoriteMoviesDetailByCustomerId(@PathVariable("customerId") Integer customerId) {

        List<Integer> favorites = movieFavoriteService.getFavoriteMoviesByCustomerId(customerId);

        for (Integer movieId : favorites) {
            APIResponse<MovieDetailResponse> favoriteDetail = getFavoriteMoviesDetailByCustomerId(movieId);
            result.add(favoriteDetail);
        }
        return result;
    }
}