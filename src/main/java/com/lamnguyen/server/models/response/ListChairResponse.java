package com.lamnguyen.server.models.response;

import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.models.dto.ChairShowtimeDTO;
import com.lamnguyen.server.models.dto.PriceBoardDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListChairResponse {
    private String uuid;
    private List<ChairShowtimeDTO> chairs;
    private PriceBoardDTO price;
}
