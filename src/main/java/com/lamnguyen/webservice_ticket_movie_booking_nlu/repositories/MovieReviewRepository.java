package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.MovieReview;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.customs.MovieReviewCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer>, MovieReviewCustomRepository {
}
