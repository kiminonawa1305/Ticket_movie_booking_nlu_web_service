package com.lamnguyen.server.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.enums.ChairType;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChairDTO implements Serializable {
    private Integer id;
    private String name;
    private ChairStatus status;
    private ChairType type;
    private Integer userId;
}
