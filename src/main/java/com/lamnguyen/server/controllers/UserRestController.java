package com.lamnguyen.server.controllers;


import com.lamnguyen.server.models.dto.CustomerDTO;
import com.lamnguyen.server.models.entity.Customer;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.CustomerService;
import com.lamnguyen.server.exceptions.ApplicationException;
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
    private CustomerService service;

    @PostMapping(value = "/check")
    public APIResponse<CustomerDTO> findCustomer(@RequestBody Map<String, String> body) {
        String apiId = body.get("apiId");
        CustomerDTO customer = service.findCustomer(apiId);
        return APIResponse.<CustomerDTO>builder()
                .message("exit!")
                .status(202)
                .data(customer)
                .build();
    }

    @PostMapping(value = "/register")
    public APIResponse<CustomerDTO> register(@RequestBody Customer customer) {
        CustomerDTO register = service.register(customer);
        if (register == null) throw new NullPointerException("Cannot register user");
        return APIResponse.<CustomerDTO>builder()
                .message("Register successfully")
                .status(202)
                .data(register)
                .build();
    }
}

