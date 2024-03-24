package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Room;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.customs.RoomCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>, RoomCustomRepository {
}
