package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.enums.ChairType;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.repositories.ChairRepository;
import com.lamnguyen.server.repositories.CinemaRepository;
import com.lamnguyen.server.repositories.PriceBoardRepository;
import com.lamnguyen.server.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("MonthDashBoardServiceImpl")
public class MonthDashBoardServiceImpl extends DashboardServiceImpl {
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
        double totalFirstWeek = 0;
        double totalSecondWeek = 0;
        double totalThirdWeek = 0;
        double totalFourthWeek = 0;
        double totalFiveWeek = 0;


        for (Ticket ticket : tickets) {
            Chair chair = chairRepository.findChairById(ticket.getChairShowTime().getChair().getId());
            ChairType chairType = chair.getType();
            Room r = roomRepository.findRoomById(chair.getRoom().getId());
            Cinema cinema = cinemaRepository.findCinemaById(r.getCinema().getId());
            PriceBoard priceBoard = priceBoardRepository.findPriceBoardByCinema_Id(cinema.getId());

            int weekOfMonth = ticket.getShowtime().getStart().get(ChronoField.ALIGNED_WEEK_OF_MONTH);
            switch (weekOfMonth) {
                case 1:
                    totalFirstWeek += calculateRevenue(chairType, priceBoard);
                    break;
                case 2:
                    totalSecondWeek += calculateRevenue(chairType, priceBoard);
                    break;
                case 3:
                    totalThirdWeek += calculateRevenue(chairType, priceBoard);
                    break;
                case 4:
                    totalFourthWeek += calculateRevenue(chairType, priceBoard);
                    break;
                default:
                    totalFiveWeek += calculateRevenue(chairType, priceBoard);
                    break;
            }
        }
        revenueByPeriod.put("FIRST_WEEK", totalFirstWeek);
        revenueByPeriod.put("SECOND_WEEK", totalSecondWeek);
        revenueByPeriod.put("THIRD_WEEK" , totalThirdWeek);
        revenueByPeriod.put("FOURTH_WEEK", totalFourthWeek);
        revenueByPeriod.put("FIFTH_WEEK", totalFiveWeek);

        return revenueByPeriod;
    }

    @Override
    protected Map<String, Integer> getNumOfTicketByPeriod(List<Ticket> tickets) {
        Map<String, Integer> numOfTicketByPeriod = new HashMap<>();
        int totalFirstWeek = 0;
        int totalSecondWeek = 0;
        int totalThirdWeek = 0;
        int totalFourthWeek = 0;
        int totalFiveWeek = 0;

        for (Ticket ticket : tickets) {
            int weekOfMonth = ticket.getShowtime().getStart().get(ChronoField.ALIGNED_WEEK_OF_MONTH);
            switch (weekOfMonth) {
                case 1:
                    totalFirstWeek++;
                    break;
                case 2:
                    totalSecondWeek++;
                    break;
                case 3:
                    totalThirdWeek++;
                    break;
                case 4:
                    totalFourthWeek++;
                    break;
                default:
                    totalFiveWeek++;
                    break;
            }
        }
        numOfTicketByPeriod.put("FIRST_WEEK", totalFirstWeek);
        numOfTicketByPeriod.put("SECOND_WEEK", totalSecondWeek);
        numOfTicketByPeriod.put("THIRD_WEEK", totalThirdWeek);
        numOfTicketByPeriod.put("FOURTH_WEEK", totalFourthWeek);
        numOfTicketByPeriod.put("FIFTH_WEEK", totalFiveWeek);

        return numOfTicketByPeriod;
    }
}
