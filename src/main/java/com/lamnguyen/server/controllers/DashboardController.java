package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.services.impl.DashboardServiceImpl;
import com.lamnguyen.server.services.impl.DayDashboardServiceImpl;
import com.lamnguyen.server.services.impl.MonthDashBoardServiceImpl;
import com.lamnguyen.server.services.impl.WeekDashboardServiceImpl;
import com.lamnguyen.server.utils.DateTimeFormat;
import com.lamnguyen.server.utils.TimeClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/dashboard/api")
public class DashboardController {
    @Qualifier("DayDashboardServiceImpl")
    @Autowired
    private DashboardServiceImpl dayDashboardService;

    @Qualifier("WeekDashboardServiceImpl")
    @Autowired
    private DashboardServiceImpl weekDashboardService;

    @Qualifier("MonthDashBoardServiceImpl")
    @Autowired
    private DashboardServiceImpl monthDashboardService;

    @GetMapping(value = "/time")
         /*
        day is 0
        week is 1
        month is 2
         */
    public APIResponse<DashboardResponse> getDashboardData(@RequestParam String from, @RequestParam String to, @RequestParam int cinemaId) {
        DashboardResponse dashboardResponse = null;
        int timeType = TimeClassification.classifyTime(from, to);
        switch (timeType) {
            case 0:
                dashboardResponse = dayDashboardService.getDashboardData(from, to, cinemaId);
                break;
            case 1:
                dashboardResponse = weekDashboardService.getDashboardData(from, to, cinemaId);
                break;
            case 2:
                dashboardResponse = monthDashboardService.getDashboardData(from, to, cinemaId);
                break;
            default:
                System.out.println("Invalid time type");
                break;
        }

        return APIResponse.<DashboardResponse>builder()
                .status(200)
                .message("Success")
                .data(dashboardResponse)
                .build();
    }
}
