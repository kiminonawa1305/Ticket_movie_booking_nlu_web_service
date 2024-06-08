package com.lamnguyen.server.repositories.customs;

import com.lamnguyen.server.models.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TicketCustomRepository {
    @Query(value = "SELECT t FROM Ticket as t WHERE t.customer.id = :userId and t.avail = true AND t.showtime.avail = True")
    List<Ticket> findTicketByUserIdAndAvailTrue(@Param("userId") Integer userId);

    @Query(value = "SELECT t FROM Ticket as t WHERE t.customer.id = :userId and t.avail = false OR t.showtime.avail = false")
    List<Ticket> findTicketByUserIdAndAvailFalse(@Param("userId") Integer userId);

    @Query(value = "SELECT t FROM Ticket as t WHERE t.id = :ticketId")
    Ticket findTicketById(@Param("ticketId") String ticketId);


    @Query(value = "SELECT t FROM Ticket as t WHERE t.customer.id = :customerId")
    List<Ticket> findTicketByCustomerId(@Param("customerId") Integer customerId);
}
