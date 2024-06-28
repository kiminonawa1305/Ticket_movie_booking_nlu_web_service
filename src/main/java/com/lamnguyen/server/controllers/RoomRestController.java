package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.entity.Room;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.RoomResponse;
import com.lamnguyen.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "room/api")
public class RoomRestController {

    @Autowired
    RoomService roomService;

    @GetMapping("/cinema/{cinemaId}")
    public APIResponse<List<RoomResponse>> getAllRoomsOfCinema(@PathVariable("cinemaId") Integer cinemaId) {
        List<Room> rooms = roomService.getAllRoomsByCinemaId(cinemaId);
        List<RoomResponse> roomResponses = rooms.stream().map(room -> convertToRoomResponse(room)).toList();
        return APIResponse.<List<RoomResponse>>builder()
                .status(202)
                .message("success")
                .data(roomResponses)
                .build();
    }

    private RoomResponse convertToRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .cinema_id(room.getCinema().getId())
                .build();
    }
}
