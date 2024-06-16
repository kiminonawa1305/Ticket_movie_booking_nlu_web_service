package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.Customer;
import com.lamnguyen.server.repositories.CustomerRepository;
import com.lamnguyen.server.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer register(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer findCustomer(String apiId) {
        return customerRepository.findByApiId(apiId);
    }
}
