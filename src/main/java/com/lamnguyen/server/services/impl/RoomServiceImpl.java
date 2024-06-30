package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.Room;
import com.lamnguyen.server.models.response.RoomResponse;
import com.lamnguyen.server.repositories.RoomRepository;
import com.lamnguyen.server.services.RoomService;
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

    @Override
    public List<Room> getAllRoomsByCinemaId(Integer cinemaId) {
        return roomRepository.findRoomsByCinemaId(cinemaId);
    }
}
