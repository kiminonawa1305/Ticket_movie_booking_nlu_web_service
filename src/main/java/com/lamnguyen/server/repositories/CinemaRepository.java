package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
