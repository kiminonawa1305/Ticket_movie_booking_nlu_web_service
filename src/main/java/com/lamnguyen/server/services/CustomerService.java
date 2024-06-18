package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.CustomerDTO;
import com.lamnguyen.server.models.entity.Customer;

public interface CustomerService {
    CustomerDTO register(Customer customer);

    CustomerDTO findCustomer(String apiId);
}
