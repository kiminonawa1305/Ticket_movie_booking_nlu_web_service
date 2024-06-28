package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.repositories.customs.ShowtimeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer>, ShowtimeCustomRepository {
    List<Showtime> findByAvailIsTrueAndStartBetweenAndMovie_Id(LocalDateTime currentDate, LocalDateTime endDate, int id);

}
