package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Cinema;

import java.util.List;

public interface CinemaService {
    Cinema insert(Cinema cinema);

    List<Cinema> findAll();
}
