package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.MovieReview;
import com.lamnguyen.server.repositories.customs.MovieReviewCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long>, MovieReviewCustomRepository {
}
