package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Customer;

public interface CustomerService {
    Customer register(String apiId);

    Customer findCustomer(String apiId);
}
