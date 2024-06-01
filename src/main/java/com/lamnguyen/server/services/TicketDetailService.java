package com.lamnguyen.server.services;

import com.lamnguyen.server.models.response.TicketDetailResponse;

public interface TicketDetailService {
    TicketDetailResponse getTicketDetailById(String ticketId);
}
