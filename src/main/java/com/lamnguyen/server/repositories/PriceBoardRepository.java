package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.PriceBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceBoardRepository extends JpaRepository<PriceBoard, Integer> {
    PriceBoard findPriceBoardByCinema_Id(Integer cinemaId);
}
