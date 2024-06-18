package com.lamnguyen.server.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lamnguyen.server.enums.ChairType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class ChairSelectedDTO extends ChairDTO {
    private Integer price;
}
