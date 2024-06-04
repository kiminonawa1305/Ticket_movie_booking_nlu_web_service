package com.lamnguyen.server.requests;

import com.lamnguyen.server.enums.ChairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChairUpdateRequest {
    private Integer userId, chairId;
    private ChairStatus status;
    private String uuid;
}
