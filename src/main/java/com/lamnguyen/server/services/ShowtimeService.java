package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.entity.Showtime;

import java.util.List;

public interface ShowtimeService {
    Showtime findById(int id);

    List<ShowtimeDTO> findShowTimeDTOByMovieId(int id);

    List<ShowtimeByCinemaResponse> findShowtimeByCinema(int id);

    PriceBoardDTO getPriceBoard(Integer showtimeId);
}
