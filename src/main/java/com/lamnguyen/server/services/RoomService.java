package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Room;
import com.lamnguyen.server.models.response.RoomResponse;

import java.util.List;

public interface RoomService {
    Room insert(Room room);

    List<Room> findAll();

    List<Room> getAllRoomsByCinemaId(Integer cinemaId);
}
