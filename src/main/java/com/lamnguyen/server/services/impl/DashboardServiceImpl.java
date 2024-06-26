package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.ChairType;
import com.lamnguyen.server.models.entity.*;
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
    private CinemaRepository cinemaRepository;
    @Autowired
    private PriceBoardRepository priceBoardRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public DashboardResponse getDashboardData(LocalDateTime from, LocalDateTime to, int cinemaId) {
        List<Ticket> tickets = ticketRepository.findTicketByShowtime_StartBetweenAndChair_Room_Cinema_Id(from, to, cinemaId);
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setTotalRevenue(calculateTotalRevenue(tickets));
        dashboardResponse.setTotalNumOfTickets(tickets.size());
        return dashboardResponse;
    }

    private double calculateTotalRevenue(List<Ticket> tickets) {
        double totalRevenue = 0;
        for (Ticket ticket : tickets) {
            Chair chair = chairRepository.findChairById(ticket.getChair().getId());
            ChairType chairType = chair.getType();
            Room r = roomRepository.findRoomById(chair.getRoom().getId());
            Cinema cinema = cinemaRepository.findCinemaById(r.getCinema().getId());
            PriceBoard priceBoard = priceBoardRepository.findPriceBoardByCinema_Id(cinema.getId());

            switch (chairType) {
                case VIP:
                    totalRevenue += priceBoard.getVip();
                    break;
                case SINGLE:
                    totalRevenue += priceBoard.getSingle();
                    break;
                case COUPLE:
                    totalRevenue += priceBoard.getCouple();
                    break;
            }
        }
        return totalRevenue;
    }
}
