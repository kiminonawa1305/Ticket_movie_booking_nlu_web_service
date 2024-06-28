package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.ShowtimeResponse;
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

    @PostMapping("/")
    public APIResponse<ShowtimeResponse> addShowtime(@RequestBody ShowtimeDTO showtimeDTO){
//        Showtime newShowtime = showtimeService.addShowtime(showtime);;
//        ShowtimeResponse newShowtimeResponse = convertToShowtimeResponse(newShowtime);
        showtimeDTO.setAvail(true);
        Showtime newShowtime = showtimeService.addShowtime(showtimeDTO);
        ShowtimeResponse newShowtimeResponse = convertToShowtimeResponse(newShowtime);
        return APIResponse.<ShowtimeResponse>builder()
                .status(202)
                .message("Success")
                .data(newShowtimeResponse)
                .build();
    }

    private ShowtimeResponse convertToShowtimeResponse(Showtime showtime){
        return ShowtimeResponse.builder()
                .id(showtime.getId())
                .start(showtime.getStart())
                .roomId(showtime.getRoom().getId())
                .movieId(showtime.getMovie().getId())
                .build();
    }
}
