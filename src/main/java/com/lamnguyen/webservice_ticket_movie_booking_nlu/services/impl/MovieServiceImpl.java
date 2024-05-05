package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.converter.ConverterDTOToResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.converter.ConverterEntityToDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.*;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieDetailResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.MovieRepository;
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

    @Autowired
    private ConverterDTOToResponse converterDTOToResponse;

    @Override
    public List<MovieDTO> findAll() {
        return convertToDTO(movieRepository.findAll());
    }

    @Override
    public List<MovieResponse> getMovieHasShowtime(LocalDate date) {
        List<Movie> movieDTOS = movieRepository.getMovieHasShowtime(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return convertToResponse(convertToDTO(movieDTOS));
    }

    @Override
    public MovieDetailResponse getMovieDetailById(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        List<MovieReview> movieReviews = movie.getMovieReviews();
        int countStar = 0;
        for(MovieReview review : movieReviews) {
            countStar += review.getStar();
        }

        return MovieDetailResponse.builder()
                .id(movie.getId())
                .idApi(movie.getIdApi())
                .vote(movieReviews.size())
                .rate(movieReviews.isEmpty() ? 5.0 :countStar / (double)movieReviews.size())
                .build();
    }

    @Override
    public void insert(Movie movie) {
        movieRepository.save(movie);
    }


    private List<MovieDTO> convertToDTO(List<Movie> movies) {
        List<MovieDTO> result = new ArrayList<MovieDTO>();
        MovieDTO dto;
        for (Movie movie : movies) {
            dto = converterEntityToDTO.convert(movie);
            result.add(dto);
        }

        return result;
    }

    private List<MovieResponse> convertToResponse(List<MovieDTO> movieDTOS) {
        List<MovieResponse> result = new ArrayList<MovieResponse>();
        MovieResponse response;
        for (MovieDTO movie : movieDTOS) {
            response = converterDTOToResponse.convert(movie);
            result.add(response);
        }

        return result;
    }


}
