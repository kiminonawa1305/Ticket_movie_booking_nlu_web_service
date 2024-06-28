package com.lamnguyen.server.models.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceManageDTO {
    private String cinemaName;
    private Double couple;
    private Double single;
    private Double vip;
    private int cinemaId;
}
