package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieDetailResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponse;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<MovieDTO> findAll();

    MovieDTO findById(Integer id);

    List<MovieResponse> getMovieHasShowtime(LocalDate date);

    void insert(Movie movie);
}
