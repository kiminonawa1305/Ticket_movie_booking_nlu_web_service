package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.ChairType;
import com.lamnguyen.server.enums.DayPeriod;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DayDashboardServiceImpl")
public class DayDashboardServiceImpl extends DashboardServiceImpl {
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
        Map<String, Double> revenueByPeriod = new HashMap<>();
        double totalMorning = 0;
        double totalAfternoon = 0;
        double totalEvening = 0;
        double totalNight = 0;
        double totalNoon = 0;

        for (Ticket ticket : tickets) {
            Chair chair = chairRepository.findChairById(ticket.getChairShowTime().getChair().getId());
            ChairType chairType = chair.getType();
            Room r = roomRepository.findRoomById(chair.getRoom().getId());
            Cinema cinema = cinemaRepository.findCinemaById(r.getCinema().getId());
            PriceBoard priceBoard = priceBoardRepository.findPriceBoardByCinema_Id(cinema.getId());

            DayPeriod period = DayPeriod.getPeriod(ticket.getShowtime().getStart());
            switch (period) {
                case MORNING:
                    totalMorning += calculateRevenue(chairType, priceBoard);
                    break;
                case AFTERNOON:
                    totalAfternoon += calculateRevenue(chairType, priceBoard);
                    break;
                case EVENING:
                    totalEvening += calculateRevenue(chairType, priceBoard);
                    break;
                case NIGHT:
                    totalNight += calculateRevenue(chairType, priceBoard);
                    break;
                case NOON:
                    totalNoon += calculateRevenue(chairType, priceBoard);
                    break;
            }
        }
        revenueByPeriod.put("MORNING", totalMorning);
        revenueByPeriod.put("AFTERNOON", totalAfternoon);
        revenueByPeriod.put("EVENING", totalEvening);
        revenueByPeriod.put("NIGHT", totalNight);
        revenueByPeriod.put("NOON", totalNoon);
        return revenueByPeriod;
    }

    @Override
    protected Map<String, Integer> getNumOfTicketByPeriod(List<Ticket> tickets) {
        Map<String, Integer> numOfTicketByPeriod = new HashMap<>();
        int totalMorning = 0;
        int totalAfternoon = 0;
        int totalEvening = 0;
        int totalNight = 0;
        int totalNoon = 0;

        for (Ticket ticket : tickets) {
            DayPeriod period = DayPeriod.getPeriod(ticket.getShowtime().getStart());

            switch (period) {
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
        numOfTicketByPeriod.put("MORNING", totalMorning);
        numOfTicketByPeriod.put("AFTERNOON", totalAfternoon);
        numOfTicketByPeriod.put("EVENING", totalEvening);
        numOfTicketByPeriod.put("NIGHT", totalNight);
        numOfTicketByPeriod.put("NOON", totalNoon);
        return numOfTicketByPeriod;
    }
}
