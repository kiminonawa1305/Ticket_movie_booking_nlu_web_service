package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.models.response.DashboardResponse;
import com.lamnguyen.server.services.impl.DashboardServiceImpl;
import com.lamnguyen.server.utils.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/dashboard/api")
public class DashboardController {
    @Autowired
    private DashboardServiceImpl dashboardService;
    @GetMapping(value = "/time")
    public APIResponse<DashboardResponse> getDashboardData(@RequestParam String from, @RequestParam String to, @RequestParam int cinemaId) {
        DashboardResponse dashboardResponse = dashboardService.getDashboardData(from, to, cinemaId);

        return APIResponse.<DashboardResponse>builder()
                .status(200)
                .message("Success")
                .data(dashboardResponse)
                .build();
    }
}
