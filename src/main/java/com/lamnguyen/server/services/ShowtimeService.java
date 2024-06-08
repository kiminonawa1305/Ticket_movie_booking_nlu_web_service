package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.entity.Showtime;

import java.util.List;

public interface ShowtimeService {
    Showtime findById(int id);

    List<Showtime> findByMovieId(int id);

    PriceBoardDTO getPriceBoard(Integer showtimeId);
}
