package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.models.response.ShowtimeManagerResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeService {
    List<Showtime> getAllShowtime();

    Showtime findById(int id);

    List<ShowtimeDTO> findShowTimeDTOByMovieId(int id, LocalDateTime currentDate);

    List<ShowtimeByCinemaResponse> findShowtimeByCinema(int id, LocalDateTime currentDate);

    PriceBoardDTO getPriceBoard(Integer showtimeId);

    ShowtimeManagerResponse addShowtime(ShowtimeDTO showtimeDTO);
}
