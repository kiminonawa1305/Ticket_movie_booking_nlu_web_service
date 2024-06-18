package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.exceptions.ApplicationException;
import com.lamnguyen.server.models.dto.CustomerDTO;
import com.lamnguyen.server.models.entity.Customer;
import com.lamnguyen.server.repositories.CustomerRepository;
import com.lamnguyen.server.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomerDTO register(Customer customer) {
        return convert(customerRepository.saveAndFlush(customer));
    }

    @Override
    public CustomerDTO findCustomer(String apiId) {
        if (customerRepository.findByApiId(apiId) == null)
            throw new ApplicationException(ApplicationException.ErrorCode.USER_NON_EXIST);
        return convert(customerRepository.findByApiId(apiId));
    }

    private CustomerDTO convert(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
