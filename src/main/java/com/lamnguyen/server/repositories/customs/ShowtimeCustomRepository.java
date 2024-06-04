package com.lamnguyen.server.repositories.customs;

import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Showtime;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;

public interface ShowtimeCustomRepository {
    @Query("SELECT st.room.cinema.priceBoard FROM Showtime st WHERE st.id = :showtimeId")
    PriceBoard findPriceBoard(@PathParam("showtimeId") Integer showtimeId);

    @Query("SELECT st FROM Showtime st " +
            "JOIN Chair c ON c.room.id = st.room.id " +
            "WHERE c.id = :chairId")
    Showtime findByChairId(Integer chairId);
}
