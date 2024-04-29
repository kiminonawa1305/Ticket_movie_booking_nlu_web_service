package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.customs;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChairCustomRepository {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Chair c SET c.status = :status WHERE c.id = :id")
    Integer updateById(@Param("id") Integer id, @Param("status") String status);
}
