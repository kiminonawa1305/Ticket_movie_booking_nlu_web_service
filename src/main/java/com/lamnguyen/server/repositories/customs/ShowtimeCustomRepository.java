package com.lamnguyen.server.repositories.customs;

import com.lamnguyen.server.models.entity.PriceBoard;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;

public interface ShowtimeCustomRepository {
    @Query("SELECT st.room.cinema.priceBoard FROM Showtime st WHERE st.id = :showtimeId")
    PriceBoard findPriceBoard(@PathParam("showtimeId") Integer showtimeId);
}
