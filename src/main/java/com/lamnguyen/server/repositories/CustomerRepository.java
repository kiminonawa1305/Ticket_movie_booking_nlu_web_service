package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Customer;
import com.lamnguyen.server.repositories.customs.CustomerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, CustomerCustomRepository {
    Customer findCustomerById(Integer id);
}
