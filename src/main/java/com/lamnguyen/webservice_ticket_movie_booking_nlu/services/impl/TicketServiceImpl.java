package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.enums.ChairStatus;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.*;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.TicketResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.ChairRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.TicketRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.TicketService;
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

    @Override
    public Ticket buyTicket(Integer chairId, Integer customerId) {
        Chair chair = chairRepository.findChairById(chairId);
        if (chair.getStatus() != null && chair.getStatus().equals(ChairStatus.SOLD.toString())) return null;
        Ticket ticket = Ticket.builder()
                .chair(Chair.builder().id(chairId).build())
                .customer(Customer.builder().id(customerId).build())
                .build();
        chairRepository.updateById(chairId, ChairStatus.SOLD.toString());
        return ticketRepository.saveAndFlush(ticket);
    }

    @Override
    public List<TicketResponse> getTicketAvail(Integer userId) {
        List<Ticket> tickets = ticketRepository.findTicketByUserIdAndAvailTrue(userId);
        RestTemplate restTemplate = new RestTemplate();
        return tickets.stream().map(ticket -> {
            return getTicketResponse(restTemplate, ticket);
        }).toList();
    }

    @Override
    public List<TicketResponse> getTicketNonAvail(Integer userId) {
        List<Ticket> tickets = ticketRepository.findTicketByUserIdAndAvailFalse(userId);
        RestTemplate restTemplate = new RestTemplate();
        return tickets.stream().map(ticket -> {
            return getTicketResponse(restTemplate, ticket);
        }).toList();
    }

    @Override
    public List<TicketResponse> getTicketByCustomerId(Integer customerId) {
        List<Ticket> tickets = ticketRepository.findTicketByCustomerId(customerId);
        RestTemplate restTemplate = new RestTemplate();
        return tickets.stream().map(ticket -> {
            return getTicketResponse(restTemplate, ticket);
        }).toList();
    }

    private TicketResponse getTicketResponse(RestTemplate restTemplate, Ticket ticket) {
        Showtime showtime = ticket.getShowtime();
        String restApiResponse = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + showtime.getMovie().getIdApi(), String.class);
        JsonObject json = new Gson().fromJson(restApiResponse, JsonObject.class);
        Chair chair = ticket.getChair();
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
                .bookedPrice(ticket.getBookedPrice())
                .available(ticket.isAvail())
                .build();
    }
}
