package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.ShowtimeService;
import com.lamnguyen.server.utils.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/showtime/api")
public class ShowtimeRestController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping(value = "/{movieId}/{date}")
    public APIResponse<List<ShowtimeByCinemaResponse>> getShowtime(@PathVariable("movieId") Integer movieId, @PathVariable("date") String dateStr) {
        LocalDateTime date = DateTimeFormat.generateStartDate(dateStr);
        List<ShowtimeByCinemaResponse> showtimeByCinemaResponses = showtimeService.findShowtimeByCinema(movieId, date);
        return APIResponse.<List<ShowtimeByCinemaResponse>>builder()
                .status(202)
                .message("Success")
                .data(showtimeByCinemaResponses)
                .build();
    }
}
