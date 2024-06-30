package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Cinema;
import com.lamnguyen.server.models.response.CinemaResponse;

import java.util.List;

public interface CinemaService {
    Cinema insert(Cinema cinema);

    List<CinemaResponse> findAllCinema();
}
