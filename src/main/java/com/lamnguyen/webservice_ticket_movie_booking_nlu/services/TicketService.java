package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Ticket;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.TicketResponse;

import java.util.List;

public interface TicketService {
    Ticket buyTicket(Integer chairId, Integer customerId);

    List<TicketResponse> getTicketAvail(Integer userId);

    List<TicketResponse> getTicketNonAvail(Integer userId);

    List<TicketResponse> getTicketByCustomerId(Integer customerId);
}
