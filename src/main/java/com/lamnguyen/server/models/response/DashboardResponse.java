package com.lamnguyen.server.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private int totalNumOfTickets;
    private Map<String, Integer> numOfTickets;
    private Map<String, Integer> revenue;
    private double totalRevenue;
}
