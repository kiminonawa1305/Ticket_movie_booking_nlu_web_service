package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.TicketDetailResponse;
import com.lamnguyen.server.services.impl.TicketDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
    @RequestMapping(value = "/ticket-detail/api")
public class TicketDetailController {
    @Autowired
    private TicketDetailServiceImpl ticketDetailService;

    @GetMapping(value = "/{ticketId}")
    public APIResponse<TicketDetailResponse> getTicketDetailById(@PathVariable("ticketId") String ticketId) {
        TicketDetailResponse ticketDetailResponse = ticketDetailService.getTicketDetailById(ticketId);
        if(ticketDetailResponse == null)
            return APIResponse.<TicketDetailResponse>builder().status(201).message("Not found").build();

        return APIResponse.<TicketDetailResponse>builder().status(202).message("Success").data(ticketDetailResponse).build();
    }
}
