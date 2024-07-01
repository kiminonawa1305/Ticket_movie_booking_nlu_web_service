package com.lamnguyen.server.controllers.admin;

import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/user/api")
public class UserManagerRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public APIResponse<List<UserDTO>> getAllAccounts(@PathVariable("userId") Integer userId) {
        List<UserDTO> users = new ArrayList<>(userService.findAll());
        users.removeIf(user -> user.getId().equals(userId));
        return APIResponse.<List<UserDTO>>builder()
                .status(202)
                .message("Get all accounts successfully")
                .data(users)
                .build();
    }

    @PostMapping("/lock/{userId}")
    public APIResponse<UserDTO> lock(@PathVariable("userId") Integer userId) {
        UserDTO user = userService.lock(userId);
        return APIResponse.<UserDTO>builder()
                .status(202)
                .message((user.isLock() ? "Lock" : "Unlock") + " account successfully")
                .data(user)
                .build();
    }
}
