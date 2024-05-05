package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailResponse {
    private Integer id;
    private String idApi;
    private Integer vote;
    private Double rate;
}
