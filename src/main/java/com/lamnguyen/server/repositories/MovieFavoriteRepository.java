package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.MovieFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieFavoriteRepository extends JpaRepository<MovieFavorite, Integer> {
    List<MovieFavorite> findByUser_Id(Integer userId);
}
