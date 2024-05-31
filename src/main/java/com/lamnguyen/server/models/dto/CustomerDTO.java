package com.lamnguyen.server.models.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerDTO implements Serializable {
    private Integer id;
    private String name;
    private String avatar;
    private String background;
    private String email;
    private List<TicketDTO> tickets;
    private List<MovieReviewDTO> movieReviews;
}
