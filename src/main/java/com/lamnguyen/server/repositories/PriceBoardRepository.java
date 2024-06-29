package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.repositories.customs.PriceBoardCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceBoardRepository extends JpaRepository<PriceBoard, Integer>, PriceBoardCustomRepository {
    
}
