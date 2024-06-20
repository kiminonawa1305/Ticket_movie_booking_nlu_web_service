package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.TicketDetailResponse;
import com.lamnguyen.server.models.response.TicketResponse;
import com.lamnguyen.server.repositories.ChairRepository;
import com.lamnguyen.server.repositories.ShowtimeRepository;
import com.lamnguyen.server.repositories.TicketRepository;
import com.lamnguyen.server.repositories.customs.TicketCustomRepository;
import com.lamnguyen.server.services.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketDetailServiceImpl implements TicketDetailService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public TicketDetailResponse getTicketDetailById(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        RestTemplate restTemplate = new RestTemplate();
        return getTicketResponse(restTemplate, ticket);
    }

    private TicketDetailResponse getTicketResponse(RestTemplate restTemplate, Ticket ticket) {
        Showtime showtime = ticket.getShowtime();
        String restApiResponse = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + showtime.getMovie().getIdApi(), String.class);
        JsonObject json = new Gson().fromJson(restApiResponse, JsonObject.class);
        Chair chair = ticket.getChair();
        Room room = chair.getRoom();
        Cinema cinema = room.getCinema();
        return TicketDetailResponse.builder()
                .id(ticket.getId())
                .poster(json.get("Poster").getAsString())
                .nameMovie(json.get("Title").getAsString())
                .duration(json.get("Runtime").getAsString())
                .nameChair(chair.getName())
                .startShowtime(showtime.getStart())
                .nameRoom(room.getName())
                .nameCinema(cinema.getName())
                .build();
    }
}
