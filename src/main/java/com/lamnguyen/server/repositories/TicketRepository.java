package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.repositories.customs.TicketCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String>, TicketCustomRepository {
    List<Ticket> findByAvailIsTrueAndShowtime_AvailIsTrueAndCustomer_Id(Integer userId);

    List<Ticket> findByAvailIsFalseAndCustomer_Id(Integer userId);

    List<Ticket> findByCustomer_Id(Integer customerId);

    List<Ticket> findTicketByShowtime_StartBetweenAndChair_Room_Cinema_Id(LocalDateTime from, LocalDateTime to, Integer cinemaId);
}
