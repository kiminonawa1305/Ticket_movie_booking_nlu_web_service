package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Showtime;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.ShowtimeRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    @Autowired
    ShowtimeRepository showtimeRepository;

    @Override
    public Showtime findById(int id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Chair findChairById(Integer showtimeId, Integer chairId) {
        Showtime showtime = findById(showtimeId);
        if (showtime != null) {
            return showtime.getRoom().getChairs().stream().filter(chair -> chair.getId().equals(chairId)).findFirst().orElse(null);
        }
        return null;
    }
}
