package com.lamnguyen.server.controllers.admin;

import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.ShowtimeManagerResponse;
import com.lamnguyen.server.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/showtime/api")
public class ShowtimeManagerRestController {
    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping("/add")
    public APIResponse<ShowtimeManagerResponse> addShowtime(@RequestBody ShowtimeDTO showtimeDTO) {
        ShowtimeManagerResponse newShowtime = showtimeService.addShowtime(showtimeDTO);
        return APIResponse.<ShowtimeManagerResponse>builder()
                .status(202)
                .message("Success")
                .data(newShowtime)
                .build();
    }
}
