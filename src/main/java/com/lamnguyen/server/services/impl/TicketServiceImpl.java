package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.TicketDetailResponse;
import com.lamnguyen.server.models.response.TicketResponse;
import com.lamnguyen.server.repositories.ChairRepository;
import com.lamnguyen.server.repositories.ChairShowtimeRepository;
import com.lamnguyen.server.repositories.ShowtimeRepository;
import com.lamnguyen.server.repositories.TicketRepository;
import com.lamnguyen.server.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ChairRepository chairRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ChairShowtimeRepository chairShowtimeRepository;

    @Override
    public Ticket buyTicket(Integer chairId, Integer customerId) {
        Showtime st = showtimeRepository.findByChairShowtimeId(chairId);
        ChairShowTime chair = chairShowtimeRepository.findById(chairId).orElse(null);
        if (chair != null && chair.getStatus() != null && chair.getStatus().equals(ChairStatus.SOLD)) return null;
        Ticket ticket = Ticket.builder()
                .chairShowTime(ChairShowTime.builder().id(chairId).build())
                .customer(Customer.builder().id(customerId).build())
                .showtime(st)
                .avail(true)
                .build();

        chair.setStatus(ChairStatus.SOLD);
        chair.setCustomer(Customer.builder().id(customerId).build());
        chairShowtimeRepository.saveAndFlush(chair);
        return ticketRepository.saveAndFlush(ticket);
    }

    @Override
    public List<TicketResponse> getTicketAvail(Integer userId) {
        List<Ticket> tickets = ticketRepository.findByAvailIsTrueAndShowtime_AvailIsTrueAndCustomer_Id(userId);
        RestTemplate restTemplate = new RestTemplate();
        return tickets.stream().map(ticket -> getTicketResponse(restTemplate, ticket)).toList();
    }

    @Override
    public List<TicketResponse> getTicketNonAvail(Integer userId) {
        List<Ticket> tickets = ticketRepository.findByAvailIsFalseAndCustomer_Id(userId);
        RestTemplate restTemplate = new RestTemplate();
        return tickets.stream().map(ticket -> getTicketResponse(restTemplate, ticket)).toList();
    }

    @Override
    public List<TicketResponse> getTicketByCustomerId(Integer customerId) {
        List<Ticket> tickets = ticketRepository.findByCustomer_Id(customerId);
        RestTemplate restTemplate = new RestTemplate();
        return tickets.stream().map(ticket -> getTicketResponse(restTemplate, ticket)).toList();
    }

    private TicketResponse getTicketResponse(RestTemplate restTemplate, Ticket ticket) {
        Showtime showtime = ticket.getShowtime();
        String restApiResponse = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + showtime.getMovie().getIdApi(), String.class);
        JsonObject json = new Gson().fromJson(restApiResponse, JsonObject.class);
        Chair chair = ticket.getChairShowTime().getChair();
        Room room = chair.getRoom();
        Cinema cinema = room.getCinema();
        return TicketResponse.builder()
                .id(ticket.getId())
                .poster(json.get("Poster").getAsString())
                .nameMovie(json.get("Title").getAsString())
                .nameChair(chair.getName())
                .startShowtime(showtime.getStart())
                .nameRoom(room.getName())
                .nameCinema(cinema.getName())
                .available(ticket.isAvail())
                .build();
    }

    @Override
    public TicketDetailResponse getTicketDetailById(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        RestTemplate restTemplate = new RestTemplate();
        return getTicketDetailResponse(restTemplate, ticket);
    }

    private TicketDetailResponse getTicketDetailResponse(RestTemplate restTemplate, Ticket ticket) {
        Showtime showtime = ticket.getShowtime();
        String restApiResponse = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + showtime.getMovie().getIdApi(), String.class);
        JsonObject json = new Gson().fromJson(restApiResponse, JsonObject.class);
        Chair chair = ticket.getChairShowTime().getChair();
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
