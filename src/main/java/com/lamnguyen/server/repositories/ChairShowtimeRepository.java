package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.ChairShowTime;
import com.lamnguyen.server.repositories.customs.ChairCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairShowtimeRepository extends JpaRepository<ChairShowTime, Integer>, ChairCustomRepository {
    ChairShowTime findByChair_Id(Integer id);
}
