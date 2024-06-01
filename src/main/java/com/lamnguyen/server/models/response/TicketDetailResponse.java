package com.lamnguyen.server.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TicketDetailResponse implements Serializable {
    private String id;
    private String nameCinema;
    private String nameMovie;
    private String nameChair;
    private String duration;
    private LocalDateTime startShowtime;
    private String nameRoom;
    private String poster;
}
