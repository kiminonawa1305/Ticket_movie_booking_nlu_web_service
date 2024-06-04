package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Room;

import java.util.List;

public interface RoomService {
    Room insert(Room room);

    List<Room> findAll();
}
