package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.ChairDTO;

public interface ChairService {
    String URL = "chair-status";

    String createKeyChairStatus(Integer showtimeId);

    ChairDTO updateChairStatus(Integer showtimeId, Integer chairId, String status);
}
