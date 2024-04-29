package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Showtime;

public interface ShowtimeService {
    Showtime findById(int id);

    Chair findChairById(Integer showtimeId, Integer chairId);
}
