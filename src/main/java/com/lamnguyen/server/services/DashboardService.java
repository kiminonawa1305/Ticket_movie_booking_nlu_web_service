package com.lamnguyen.server.services;

import com.lamnguyen.server.models.response.DashboardResponse;

import java.time.LocalDateTime;

public interface DashboardService {
    DashboardResponse getDashboardData(String from, String to, int cinemaId);
}
