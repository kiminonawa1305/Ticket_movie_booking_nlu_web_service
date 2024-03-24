package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair, Integer> {
}
