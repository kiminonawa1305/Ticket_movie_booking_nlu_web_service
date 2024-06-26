package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.ChairType;
import com.lamnguyen.server.enums.DayPeriod;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.repositories.*;
import com.lamnguyen.server.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public DashboardResponse getDashboardData(String from, String to, int cinemaId) {
        LocalDateTime fromDateTime = LocalDateTime.parse(from);
        LocalDateTime toDateTime = LocalDateTime.parse(to);
        List<Ticket> tickets = ticketRepository.getTicketByShowtime_StartBetweenAndChair_Room_Cinema_Id(fromDateTime, toDateTime, cinemaId);
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setTotalRevenue(calculateTotalRevenue(tickets));
        dashboardResponse.setTotalNumOfTickets(tickets.size());
        dashboardResponse.setNumOfTickets(getNumOfTicketByPeriod(tickets));
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

    private Map<String, Integer> getNumOfTicketByPeriod(List<Ticket> tickets){
        Map<String, Integer> numOfTicketByPeriod = new HashMap<>();
        int totalMorning = 0;
        int totalAfternoon = 0;
        int totalEvening = 0;
        int totalNight = 0;
        int totalNoon = 0;

        for(Ticket ticket : tickets){
            DayPeriod period = DayPeriod.getPeriod(ticket.getShowtime().getStart());

            switch (period){
                case MORNING:
                    totalMorning++;
                    break;
                case AFTERNOON:
                    totalAfternoon++;
                    break;
                case EVENING:
                    totalEvening++;
                    break;
                case NIGHT:
                    totalNight++;
                    break;
                case NOON:
                    totalNoon++;
                    break;
            }
        }
        numOfTicketByPeriod.put("Morning", totalMorning);
        numOfTicketByPeriod.put("Afternoon", totalAfternoon);
        numOfTicketByPeriod.put("Evening", totalEvening);
        numOfTicketByPeriod.put("Night", totalNight);
        numOfTicketByPeriod.put("Noon", totalNoon);
        return numOfTicketByPeriod;
    }

}
