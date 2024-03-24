package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    void insert(Movie movie);
}
