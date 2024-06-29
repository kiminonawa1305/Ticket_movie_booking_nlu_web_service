package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.entity.User;

public interface UserService {
    UserDTO register(User user);

    UserDTO findByEmail(String email);
}
