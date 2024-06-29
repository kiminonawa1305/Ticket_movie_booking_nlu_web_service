package com.lamnguyen.server.controllers;


import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.entity.User;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/user/api")
public class UserRestController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/check")
    public APIResponse<UserDTO> findUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        UserDTO user = service.findByEmail(email);
        return APIResponse.<UserDTO>builder()
                .message("exit!")
                .status(202)
                .data(user)
                .build();
    }

    @PostMapping(value = "/register")
    public APIResponse<UserDTO> register(@RequestBody User user) {
        UserDTO register = service.register(user);
        if (register == null) throw new NullPointerException("Cannot register user");
        return APIResponse.<UserDTO>builder()
                .message("Register successfully")
                .status(202)
                .data(register)
                .build();
    }
}

