package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Service("MonthDashBoardServiceImpl")
public class MonthDashBoardServiceImpl extends DashboardServiceImpl{
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ChairRepository chairRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private PriceBoardRepository priceBoardRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    protected Map<String, Double> calculateRevenueByPeriod(List<Ticket> tickets) {
        return Map.of();
    }

    @Override
    protected Map<String, Integer> getNumOfTicketByPeriod(List<Ticket> tickets) {
        return Map.of();
    }
}
