package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.models.response.MovieResponseRestApi;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieService {

    List<MovieResponse> getAllMovies();

    MovieDTO findById(Integer id);

    List<MovieResponse> getMovieHasShowtime(LocalDateTime date);

    Movie insert(Movie movie);

    MovieResponseRestApi getMovieResponseRestApi(String idApi);

    MovieResponse convertMovieResponseRestApiToMovieResponse(Movie movie, MovieResponseRestApi movieResponseRestApi);
}
