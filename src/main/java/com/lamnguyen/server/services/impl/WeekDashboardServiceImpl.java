package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.ChairType;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("WeekDashboardServiceImpl")
public class WeekDashboardServiceImpl extends DashboardServiceImpl {
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
        double totalMonday = 0;
        double totalTuesday = 0;
        double totalWednesday = 0;
        double totalThursday = 0;
        double totalFriday = 0;
        double totalSaturday = 0;
        double totalSunday = 0;

        for (Ticket ticket : tickets) {
            Chair chair = chairRepository.findChairById(ticket.getChair().getId());
            ChairType chairType = chair.getType();
            Room r = roomRepository.findRoomById(chair.getRoom().getId());
            Cinema cinema = cinemaRepository.findCinemaById(r.getCinema().getId());
            PriceBoard priceBoard = priceBoardRepository.findPriceBoardByCinema_Id(cinema.getId());

            DayOfWeek dayOfWeek = ticket.getShowtime().getStart().getDayOfWeek();
            switch (dayOfWeek) {
                case MONDAY:
                    totalMonday += calculateRevenue(chairType, priceBoard);
                    break;
                case TUESDAY:
                    totalTuesday += calculateRevenue(chairType, priceBoard);
                    break;
                case WEDNESDAY:
                    totalWednesday += calculateRevenue(chairType, priceBoard);
                    break;
                case THURSDAY:
                    totalThursday += calculateRevenue(chairType, priceBoard);
                    break;
                case FRIDAY:
                    totalFriday += calculateRevenue(chairType, priceBoard);
                    break;
                case SATURDAY:
                    totalSaturday += calculateRevenue(chairType, priceBoard);
                    break;
                case SUNDAY:
                    totalSunday += calculateRevenue(chairType, priceBoard);
                    break;

            }
        }
        revenueByPeriod.put("MONDAY", totalMonday);
        revenueByPeriod.put("TUESDAY", totalTuesday);
        revenueByPeriod.put("WEDNESDAY", totalWednesday);
        revenueByPeriod.put("THURSDAY", totalThursday);
        revenueByPeriod.put("FRIDAY", totalFriday);
        revenueByPeriod.put("SATURDAY", totalSaturday);
        revenueByPeriod.put("SUNDAY", totalSunday);
        return revenueByPeriod;
    }

    @Override
    protected Map<String, Integer> getNumOfTicketByPeriod(List<Ticket> tickets) {
        Map<String, Integer> numOfTicketByPeriod = new HashMap<>();
        int totalMonday = 0;
        int totalTuesday = 0;
        int totalWednesday = 0;
        int totalThursday = 0;
        int totalFriday = 0;
        int totalSaturday = 0;
        int totalSunday = 0;
        for (Ticket ticket : tickets) {
            DayOfWeek dayOfWeek = ticket.getShowtime().getStart().getDayOfWeek();
            switch (dayOfWeek) {
                case MONDAY:
                    totalMonday++;
                    break;
                case TUESDAY:
                    totalTuesday++;
                    break;
                case WEDNESDAY:
                    totalWednesday++;
                    break;
                case THURSDAY:
                    totalThursday++;
                    break;
                case FRIDAY:
                    totalFriday++;
                    break;
                case SATURDAY:
                    totalSaturday++;
                    break;
                case SUNDAY:
                    totalSunday++;
                    break;
            }
        }
        numOfTicketByPeriod.put("MONDAY", totalMonday);
        numOfTicketByPeriod.put("TUESDAY", totalTuesday);
        numOfTicketByPeriod.put("WEDNESDAY", totalWednesday);
        numOfTicketByPeriod.put("THURSDAY", totalThursday);
        numOfTicketByPeriod.put("FRIDAY", totalFriday);
        numOfTicketByPeriod.put("SATURDAY", totalSaturday);
        numOfTicketByPeriod.put("SUNDAY", totalSunday);

        return numOfTicketByPeriod;
    }
}
