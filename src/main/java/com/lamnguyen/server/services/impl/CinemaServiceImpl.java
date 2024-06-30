package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.Cinema;
import com.lamnguyen.server.models.response.CinemaResponse;
import com.lamnguyen.server.repositories.CinemaRepository;
import com.lamnguyen.server.services.CinemaService;
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
    public List<CinemaResponse> findAllCinema() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        return cinemas.stream().map(cinema -> convertToCinemaResponse(cinema)).toList();
    }

    private CinemaResponse convertToCinemaResponse(Cinema cinema) {
        return CinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .build();
    }
}
