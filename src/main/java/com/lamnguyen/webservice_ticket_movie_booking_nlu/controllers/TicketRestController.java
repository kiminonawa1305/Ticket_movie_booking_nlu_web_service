package com.lamnguyen.webservice_ticket_movie_booking_nlu.controllers;


import com.lamnguyen.webservice_ticket_movie_booking_nlu.APIResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Ticket;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ticket/api")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "/")
    public APIResponse<String> helloWorld() {
        APIResponse<String> response = new APIResponse<>();
        response.setStatus(200);
        response.setData("Hello World");
        response.setMessage("Successfully retrieved tickets");
        return response;
    }

    @PostMapping(value = "/buy")
    public APIResponse<List<Ticket>> buyTicket(@RequestBody Map<String, Object> requestBody) {
        List<Integer> chairIds = (List<Integer>) requestBody.get("chair-id");
        Integer customerId = (Integer) requestBody.get("customer-id");
        List<Ticket> tickets = new ArrayList<>();
        for (Integer chairId : chairIds) {
            Ticket ticket = ticketService.buyTicket(chairId, customerId);
            if (ticket != null)
                tickets.add(ticket);
        }
        APIResponse<List<Ticket>> response = new APIResponse<>();
        response.setStatus(202);
        response.setData(tickets);
        response.setMessage(tickets.isEmpty() ? "Failed to buy ticket" : "Successfully bought ticket");
        return response;
    }
}

