package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.repositories.customs.MovieCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, MovieCustomRepository {
    Movie findByIdApi(String idApi);
}
