package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/showtime/api")
public class ShowtimeRestController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping(value = "/{movieId}")
    public APIResponse<List<ShowtimeByCinemaResponse>> getShowtime(@PathVariable("movieId") Integer movieId) {
        List<ShowtimeByCinemaResponse> showtimeByCinemaResponses = showtimeService.findShowtimeByCinema(movieId);
        return APIResponse.<List<ShowtimeByCinemaResponse>>builder()
                .status(202)
                .message("Success")
                .data(showtimeByCinemaResponses)
                .build();
    }
}
