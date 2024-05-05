package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieResponse implements Serializable {
    private Integer id;
    private String idApi;
}
