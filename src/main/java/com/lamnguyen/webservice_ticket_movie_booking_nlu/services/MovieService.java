package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;

import java.util.List;

public interface MovieService {
    List<MovieDTO> findAll();

    List<MovieDTO> getMovieHasShowtime();

    void insert(Movie movie);
}
