package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.repositories.*;
import com.lamnguyen.server.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ChairRepository chairRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private PriceBoardRepository priceBoardRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public DashboardResponse getDashboardData(LocalDateTime from, LocalDateTime to) {
        List<Ticket> tickets = ticketRepository.findTicketByShowtime_StartBetween(from, to);
        DashboardResponse dashboardResponse = new DashboardResponse();

        dashboardResponse.setTotalNumOfTickets(tickets.size());
        return dashboardResponse;
    }
}
