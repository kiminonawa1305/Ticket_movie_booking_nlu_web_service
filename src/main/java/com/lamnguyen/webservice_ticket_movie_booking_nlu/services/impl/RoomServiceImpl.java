package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Room;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.RoomRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room insert(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
