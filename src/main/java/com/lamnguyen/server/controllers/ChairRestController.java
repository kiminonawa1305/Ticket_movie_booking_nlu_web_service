package com.lamnguyen.server.controllers;


import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.ListChairResponse;
import com.lamnguyen.server.requests.ChairUpdateRequest;
import com.lamnguyen.server.services.ChairService;
import com.lamnguyen.server.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{showtime_id}")
    public APIResponse<ListChairResponse> getKeyChairStatus(@PathVariable("showtime_id") Integer showtimeId) {
        String uuid = chairService.createKeyChairStatus(showtimeId);
        return APIResponse.<ListChairResponse>builder()
                .status(202)
                .message("Success")
                .data(ListChairResponse.builder()
                        .uuid(uuid)
                        .chairs(chairService.getChairStatus(showtimeId))
                        .price(showtimeService.getPriceBoard(showtimeId))
                        .build())
                .build();
    }

    @PostMapping(value = "/update")
    public APIResponse<Void> updateChairStatus(@RequestBody ChairUpdateRequest chairUpdateRequest) {
        ChairDTO chair = chairService.updateChairStatus(chairUpdateRequest);
        if (chair == null) {
            return APIResponse.<Void>builder()
                    .status(500)
                    .message("Fail")
                    .build();

        }
        return APIResponse.<Void>builder()
                .status(202)
                .message("Success")
                .build();
    }
}

