package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Cinema;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.CinemaRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;


    @Override
    public Cinema insert(Cinema cinema) {
        return cinemaRepository.saveAndFlush(cinema);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }
}
