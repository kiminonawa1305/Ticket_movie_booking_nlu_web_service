package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/user/api")
public class UserManagerRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public APIResponse<List<UserDTO>> getAllAccounts() {
        return APIResponse.<List<UserDTO>>builder()
                .status(202)
                .message("Get all accounts successfully")
                .data(userService.findAll())
                .build();
    }
}
