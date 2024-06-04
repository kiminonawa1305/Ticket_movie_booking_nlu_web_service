package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.requests.ChairUpdateRequest;

import java.util.List;

public interface ChairService {
    String URL = "chair-status";

    String createKeyChairStatus(Integer showtimeId);

    List<ChairDTO> getChairStatus(Integer showtimeId);

    ChairDTO updateChairStatus(ChairUpdateRequest chairUpdateRequest);
}
