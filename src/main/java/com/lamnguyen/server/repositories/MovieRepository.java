package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.repositories.customs.MovieCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, MovieCustomRepository {
}
