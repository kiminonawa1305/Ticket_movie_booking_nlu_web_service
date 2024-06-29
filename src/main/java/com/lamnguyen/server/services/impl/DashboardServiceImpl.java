package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.ChairType;
import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.repositories.*;
import com.lamnguyen.server.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Component
public abstract class  DashboardServiceImpl implements DashboardService {
    @Autowired
    protected TicketRepository ticketRepository;
    @Autowired
    protected ChairRepository chairRepository;
    @Autowired
    protected CinemaRepository cinemaRepository;
    @Autowired
    protected PriceBoardRepository priceBoardRepository;
    @Autowired
    protected RoomRepository roomRepository;
    @Override
    public DashboardResponse getDashboardData(String from, String to, int cinemaId) {
        LocalDateTime fromDateTime = LocalDateTime.parse(from);
        LocalDateTime toDateTime = LocalDateTime.parse(to);

        List<Ticket> tickets = ticketRepository.getTicketByShowtime_StartBetweenAndChair_Room_Cinema_Id(fromDateTime, toDateTime, cinemaId);
        DashboardResponse dashboardResponse = new DashboardResponse();

        Map<String, Double> revenueByPeriod = calculateRevenueByPeriod(tickets);
        dashboardResponse.setTotalRevenue(calculateTotalRevenue(revenueByPeriod));
        dashboardResponse.setTotalNumOfTickets(tickets.size());
        dashboardResponse.setNumOfTickets(getNumOfTicketByPeriod(tickets));
        dashboardResponse.setRevenue(revenueByPeriod);

        return dashboardResponse;
    }
    protected abstract Map<String, Double> calculateRevenueByPeriod(List<Ticket> tickets) ;

    protected  double calculateTotalRevenue(Map<String, Double> revenueByPeriod){
        double total = 0;
        for (Map.Entry<String, Double> entry : revenueByPeriod.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    protected double calculateRevenue(ChairType chairType, PriceBoard priceBoard){
        double revenue = 0;
        switch (chairType) {
            case VIP:
                revenue += priceBoard.getVip();
                break;
            case SINGLE:
                revenue += priceBoard.getSingle();
                break;
            case COUPLE:
                revenue += priceBoard.getCouple();
                break;
        }
        return revenue;
    }

    protected abstract Map<String, Integer> getNumOfTicketByPeriod(List<Ticket> tickets);

}
