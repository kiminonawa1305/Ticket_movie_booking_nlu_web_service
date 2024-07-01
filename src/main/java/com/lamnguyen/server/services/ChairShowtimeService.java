package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.models.dto.ChairShowtimeDTO;
import com.lamnguyen.server.models.entity.Room;
import com.lamnguyen.server.requests.ChairUpdateRequest;

import java.util.List;

public interface ChairShowtimeService {
    String URL = "chair-status";

    String createKeyChairStatus(Integer showtimeId);

    List<ChairShowtimeDTO> getChairShowtime(Integer showtimeId);

    ChairShowtimeDTO updateChairStatus(ChairUpdateRequest chairUpdateRequest);

    List<ChairShowtimeDTO> addChairShowtime(Integer showtimeId, Room room);
}
