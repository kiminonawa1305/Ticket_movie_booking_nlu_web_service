package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Ticket;
import com.lamnguyen.server.repositories.customs.TicketCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String>, TicketCustomRepository {
}
