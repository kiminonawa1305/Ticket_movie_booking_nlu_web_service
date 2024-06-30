package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.MovieFavorite;
import com.lamnguyen.server.models.response.MovieResponse;

import java.util.List;

public interface MovieFavoriteService {
    List<MovieResponse> getFavoriteMoviesByUserId(Integer userId);

    MovieFavorite getFavoriteMoviesByUserIdAndMovieId(Integer userId, Integer movieId);

    void setFavourite(Integer userId, Integer movieId);
}
