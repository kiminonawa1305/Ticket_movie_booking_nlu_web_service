package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.ChairShowTime;
import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.repositories.customs.ShowtimeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer>, ShowtimeCustomRepository {
    List<Showtime> findByAvailIsTrueAndMovie_Id(int id);
}
