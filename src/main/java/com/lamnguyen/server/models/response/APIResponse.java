package com.lamnguyen.server.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;
}
