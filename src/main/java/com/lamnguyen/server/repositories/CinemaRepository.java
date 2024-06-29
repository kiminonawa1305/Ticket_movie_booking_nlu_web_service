package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Cinema;
import com.lamnguyen.server.repositories.customs.CinemaCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long>, CinemaCustomRepository {
    Cinema findCinemaById(Integer id);
}
