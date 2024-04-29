package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChairDTO implements Serializable {
    private Integer id;
    private String name;
    private String status;
    private String describe;
}
