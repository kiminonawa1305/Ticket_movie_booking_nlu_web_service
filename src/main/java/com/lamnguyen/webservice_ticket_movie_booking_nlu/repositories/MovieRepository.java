package com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.customs.MovieCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>, MovieCustomRepository {

}
