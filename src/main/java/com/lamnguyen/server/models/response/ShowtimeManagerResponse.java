package com.lamnguyen.server.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeManagerResponse {
    private Integer id, movieId, roomId, totalSear, totalSeatAvailable;
    private LocalDateTime start, end;
    private String movieName, roomName;
    private Integer duration;
}
