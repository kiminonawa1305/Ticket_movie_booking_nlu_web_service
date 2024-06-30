package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.entity.Cinema;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.CinemaResponse;
import com.lamnguyen.server.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cinema/api")
public class CinemaRestController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/")
    public APIResponse<List<CinemaResponse>> getAllCinema() {
        List<CinemaResponse> cinemaResponses = cinemaService.findAllCinema();
        return APIResponse.<List<CinemaResponse>>builder()
                .status(202)
                .message("success")
                .data(cinemaResponses)
                .build();
    }
}
