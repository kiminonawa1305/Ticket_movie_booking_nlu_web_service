package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Customer;

public interface CustomerService {
    Customer register(Customer customer);

    Customer findCustomer(String apiId);
}
