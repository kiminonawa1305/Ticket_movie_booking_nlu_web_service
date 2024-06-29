package com.lamnguyen.server.repositories.customs;

import com.lamnguyen.server.models.entity.Ticket;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface TicketCustomRepository {
    @Query("SELECT t " +
            "FROM Ticket t " +
            "inner join Showtime st  on t.showtime.id = st.id " +
            "inner join Room r on st.room.id = r.id " +
            "inner join Cinema c on r.cinema.id = c.id " +
            "WHERE t.showtime.start BETWEEN :from AND :to AND r.cinema.id = :cinemaId")
    List<Ticket> getTicketByShowtime_StartBetweenAndChair_Room_Cinema_Id(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("cinemaId") int cinemaId);
}
