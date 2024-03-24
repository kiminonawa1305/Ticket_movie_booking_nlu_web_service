package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.ChairStatus;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.customs.ChairStatusCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairStatusRepository extends JpaRepository<ChairStatus, Integer>, ChairStatusCustomRepository {
}
