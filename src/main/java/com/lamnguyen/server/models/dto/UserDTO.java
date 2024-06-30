package com.lamnguyen.server.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lamnguyen.server.models.entity.User;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    Integer id;
    String fullName;
    String email;
    String phone;
    boolean lock;
}