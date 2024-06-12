package com.lamnguyen.server.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeDTO {
    private Integer id;
    private LocalDateTime start;
    private boolean avail;
}