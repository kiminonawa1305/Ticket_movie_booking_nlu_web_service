package com.lamnguyen.server.repositories.customs;

import com.lamnguyen.server.models.entity.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieCustomRepository {

    @Modifying
    @Query(value = "SELECT distinct st.movie FROM Showtime st " +
            "WHERE st.avail = true AND st.start between :current_date AND :next_date")
    List<Movie> getMovieHasShowtime(@Param("current_date") LocalDateTime currentDate, @Param("next_date") LocalDateTime nextDate);
}
