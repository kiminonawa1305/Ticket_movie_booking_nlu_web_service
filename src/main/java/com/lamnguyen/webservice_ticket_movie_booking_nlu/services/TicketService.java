package com.lamnguyen.webservice_ticket_movie_booking_nlu.services;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Ticket;

public interface TicketService {
    Ticket buyTicket(Integer chairId, Integer customerId);
}
