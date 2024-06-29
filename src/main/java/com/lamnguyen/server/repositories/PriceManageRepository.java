package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.dto.PriceManageDTO;
import com.lamnguyen.server.models.entity.PriceBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceManageRepository extends JpaRepository<PriceBoard, Long> {

    @Query("SELECT new  com.lamnguyen.server.models.dto.PriceManageDTO(c.name, pb.couple, pb.single, pb.vip, c.id) " +
            "FROM PriceBoard pb JOIN pb.cinema c")
    List<PriceManageDTO> findAllPriceInfo();

    @Modifying
    @Query("UPDATE PriceBoard pb SET pb.couple = :couple, pb.single = :single, pb.vip = :vip WHERE pb.cinema.id = :cinemaId")
    void updatePriceBoardByCinemaId(@Param("couple") Double couple, @Param("single") Double single, @Param("vip") Double vip, @Param("cinemaId") int cinemaId);

}
