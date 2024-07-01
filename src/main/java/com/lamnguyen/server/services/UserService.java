package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.entity.User;

import java.util.List;

public interface UserService {
    UserDTO register(User user);

    UserDTO login(String email);

    List<UserDTO> findAll();

    UserDTO lock(Integer userId);
}
