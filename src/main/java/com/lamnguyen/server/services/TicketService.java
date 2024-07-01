package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.models.response.TicketDetailResponse;
import com.lamnguyen.server.models.response.TicketResponse;

import java.util.List;

public interface TicketService {
    Ticket buyTicket(Integer showtimeId, Integer chairShowTimeId, Integer userId);

    List<TicketResponse> getTicketAvail(Integer userId);

    List<TicketResponse> getTicketNonAvail(Integer userId);

    List<TicketResponse> getTicketByUserId(Integer userId);

    TicketDetailResponse getTicketDetailById(String ticketId);
}
