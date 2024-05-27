package com.lamnguyen.webservice_ticket_movie_booking_nlu.controllers;


import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.ChairDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.APIResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.ChairService;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/chair/api")
public class ChairRestController {
    @Autowired
    private ChairService chairService;
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping(value = "/")
    public APIResponse<List<Chair>> helloWorld() {
        return null;
    }

    @PostMapping(value = "/chair-status")
    public APIResponse<String> getKeyChairStatus(@RequestBody Map<String, String> requestBody) {
        Integer showtimeId = Integer.valueOf(requestBody.get("showtime-id"));
        String uuid = chairService.createKeyChairStatus(showtimeId);
        APIResponse<String> response = new APIResponse<>();
        return APIResponse.<String>builder()
                .status(202)
                .message("Success")
                .data(uuid)
                .build();
    }

    @PostMapping(value = "/chair-status/update")
    public APIResponse<ChairDTO> updateChairStatus(@RequestBody Map<String, String> requestBody) {
        APIResponse<ChairDTO> response = new APIResponse<>();
        Integer showtimeId = Integer.valueOf(requestBody.get("showtime-id"));
        Integer chairId = Integer.valueOf(requestBody.get("chair-id"));
        String status = requestBody.get("status");
        ChairDTO chair = chairService.updateChairStatus(showtimeId, chairId, status);
        if (chair == null) {
            return APIResponse.<ChairDTO>builder()
                    .status(500)
                    .message("Fail")
                    .build();

        }
        return APIResponse.<ChairDTO>builder()
                .status(202)
                .message("Success")
                .data(chair)
                .build();
    }
}

