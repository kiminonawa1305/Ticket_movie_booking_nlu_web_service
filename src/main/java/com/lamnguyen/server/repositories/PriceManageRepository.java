package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.dto.PriceManageDTO;
import com.lamnguyen.server.models.entity.PriceBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceManageRepository extends JpaRepository<PriceBoard, Long> {

    @Query("SELECT new  com.lamnguyen.server.models.dto.PriceManageDTO(c.name, pb.couple, pb.single, pb.vip, c.id) " +
            "FROM PriceBoard pb JOIN pb.cinema c")
    List<PriceManageDTO> findAllPriceInfo();


}
