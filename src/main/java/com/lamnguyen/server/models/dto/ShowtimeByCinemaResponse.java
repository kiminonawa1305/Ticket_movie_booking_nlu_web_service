package com.lamnguyen.server.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeByCinemaResponse {
    private String urlImageCinema;
    private String nameCinema;
    private String addressCinema;
    private String detailAddressCinema;
    private List<TimeSeat> timeSeats;
}