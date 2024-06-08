package com.lamnguyen.server.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketResponse implements Serializable {
    private String id;
    private String nameCinema;
    private String nameMovie;
    private String nameChair;
    private LocalDateTime startShowtime;
    private String nameRoom;
    private String poster;
    private double bookedPrice;
    private boolean available;
}
