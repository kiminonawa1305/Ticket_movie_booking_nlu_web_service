package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.entity.Showtime;

public interface ShowtimeService {
    Showtime findById(int id);

    Chair findChairById(Integer showtimeId, Integer chairId);

    PriceBoardDTO getPriceBoard(Integer showtimeId);
}
