package com.lamnguyen.server.services;

import com.lamnguyen.server.models.response.DashboardResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public interface DashboardService {
    DashboardResponse getDashboardData(String from, String to, int cinemaId);
}
