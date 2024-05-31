package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.response.MovieResponse;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<MovieDTO> findAll();

    MovieDTO findById(Integer id);

    List<MovieResponse> getMovieHasShowtime(LocalDate date);

    void insert(Movie movie);
}
