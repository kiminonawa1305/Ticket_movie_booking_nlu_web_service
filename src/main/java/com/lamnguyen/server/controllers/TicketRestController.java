package com.lamnguyen.server.controllers;


import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.TicketResponse;
import com.lamnguyen.server.services.TicketService;
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
    public APIResponse<Void> helloWorld() {
        return APIResponse.<Void>builder().status(202).message("Hello World").build();
    }

    @GetMapping(value = "/all/{customerId}")
    public APIResponse<List<TicketResponse>> getAllTickets(@PathVariable Integer customerId) {
        if(customerId == null) {
            return APIResponse.<List<TicketResponse>>builder().status(401).message("Unauthorized").build();
        }else{
            List<TicketResponse> ticketResponses = ticketService.getTicketByCustomerId(customerId);
            return APIResponse.<List<TicketResponse>>builder().status(202).message("Success").data(ticketResponses).build();
        }
    }

    @GetMapping(value = "/avail")
    public APIResponse<List<TicketResponse>> getTicketAvail(@RequestHeader Map<String, String> headers) {
        String userIdString = headers.get("user-id");
        if (userIdString == null)
            return APIResponse.<List<TicketResponse>>builder().status(401).message("Unauthorized").build();

        Integer userId = Integer.parseInt(userIdString);
        List<TicketResponse> responses = ticketService.getTicketAvail(userId);
        return APIResponse.<List<TicketResponse>>builder().status(202).message("Success").data(responses).build();
    }

    @GetMapping(value = "/non-avail")
    public APIResponse<List<TicketResponse>> getTicketNonAvail(@RequestHeader Map<String, String> headers) {
        String userIdString = headers.get("user-id");
        if (userIdString == null)
            return APIResponse.<List<TicketResponse>>builder().status(401).message("Unauthorized").build();

        Integer userId = Integer.parseInt(userIdString);
        List<TicketResponse> responses = ticketService.getTicketNonAvail(userId);
        if (responses == null)
            return APIResponse.<List<TicketResponse>>builder().status(201).message("Not found").build();
        return APIResponse.<List<TicketResponse>>builder().status(202).message("Success").data(responses).build();
    }

    @PostMapping(value = "/buy")
    public APIResponse<List<Ticket>> buyTicket(@RequestBody Map<String, Object> requestBody) {
        List<Integer> chairIds = (List<Integer>) requestBody.get("chair-id");
        Integer customerId = (Integer) requestBody.get("customer-id");
        List<Ticket> tickets = new ArrayList<>();
        for (Integer chairId : chairIds) {
            Ticket ticket = ticketService.buyTicket(chairId, customerId);
            if (ticket != null) tickets.add(ticket);
        }
        return APIResponse.<List<Ticket>>builder().status(202).message(tickets.isEmpty() ? "Failed to buy ticket" : "Successfully bought ticket").data(tickets).build();
    }
}

