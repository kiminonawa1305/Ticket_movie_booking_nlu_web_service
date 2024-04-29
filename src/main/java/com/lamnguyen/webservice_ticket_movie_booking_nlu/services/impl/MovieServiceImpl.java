package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.converter.ConverterEntityToDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Room;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.MovieRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ConverterEntityToDTO converterEntityToDTO;

    @Override
    public List<MovieDTO> findAll() {
        return convert(movieRepository.findAll());
    }

    @Override
    public List<MovieDTO> getMovieHasShowtime(LocalDate date) {
        return convert(movieRepository.getMovieHasShowtime(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX)));
    }

    @Override
    public void insert(Movie movie) {
        movieRepository.save(movie);
    }


    public List<MovieDTO> convert(List<Movie> movies) {
        List<MovieDTO> result = new ArrayList<MovieDTO>();
        MovieDTO dto;
        for (Movie movie : movies) {
            dto = converterEntityToDTO.convert(movie);
            result.add(dto);
        }

        return result;
    }
}
