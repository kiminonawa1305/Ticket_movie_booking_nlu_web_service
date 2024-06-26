package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.repositories.customs.TicketCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String>, TicketCustomRepository {
    List<Ticket> findByAvailIsTrueAndShowtime_AvailIsTrueAndUser_Id(Integer userId);

    List<Ticket> findByAvailIsFalseAndUser_Id(Integer userId);

    List<Ticket> findByUser_Id(Integer userId);
}
