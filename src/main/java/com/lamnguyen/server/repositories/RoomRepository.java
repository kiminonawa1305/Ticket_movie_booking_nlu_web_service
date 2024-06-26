package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Cinema;
import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Room;
import com.lamnguyen.server.repositories.customs.RoomCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>, RoomCustomRepository {
    Room findRoomById(Integer id);
}
