package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.enums.ChairStatus;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Customer;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Ticket;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.ChairRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.TicketRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
