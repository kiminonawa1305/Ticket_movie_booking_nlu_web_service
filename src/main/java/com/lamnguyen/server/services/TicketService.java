package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.models.response.TicketResponse;

import java.util.List;

public interface TicketService {
    Ticket buyTicket(Integer chairId, Integer customerId);

    List<TicketResponse> getTicketAvail(Integer userId);

    List<TicketResponse> getTicketNonAvail(Integer userId);

    List<TicketResponse> getTicketByCustomerId(Integer customerId);
}
