package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Customer;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.customs.CustomerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, CustomerCustomRepository {
    Customer findCustomerById(Integer id);
}
