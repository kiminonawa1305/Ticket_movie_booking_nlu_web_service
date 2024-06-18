package com.lamnguyen.server.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.lamnguyen.server.models.entity.Customer}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    Integer id;
    String apiId;
    String fullName;
    String email;
    String phone;
}