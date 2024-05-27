package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.converter.ConverterDTOToResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.converter.ConverterEntityToDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.MovieReview;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieDetailResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponse;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponseRestApi;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.MovieRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public MovieDTO findById(Integer id) {
        return convertToDTO(movieRepository.findById(id).orElse(null));
    }

    @Override
    public List<MovieResponse> getMovieHasShowtime(LocalDate date) {
        List<Movie> movies = movieRepository.getMovieHasShowtime(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));

        RestTemplate restTemplate = new RestTemplate();
        return movies.stream().map(movie -> {
            String data = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + movie.getIdApi(), String.class);
            MovieResponseRestApi restApi = new Gson().fromJson(data, MovieResponseRestApi.class);
            MovieResponse response = MovieResponse.builder()
                    .id(movie.getId())
                    .title(restApi.getTitle())
                    .poster(restApi.getPoster())
                    .genre(restApi.getGenre())
                    .duration(restApi.getRuntime())
                    .vote(movie.getMovieReviews().size())
                    .rate(rating(movie))
                    .build();
            return response;
        }).toList();
    }

    private double rating(Movie movie) {
        List<MovieReview> movieReviews = movie.getMovieReviews();
        int countStar = movieReviews.stream().map(MovieReview::getStar).reduce(0, Integer::sum);
        return movieReviews.isEmpty() ? 5.0 : countStar / (double) movieReviews.size();
    }

    @Override
    public void insert(Movie movie) {
        movieRepository.save(movie);
    }

    private MovieDTO convertToDTO(Movie movie) {
        return converterEntityToDTO.convert(movie);
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

    private List<MovieResponseRestApi> convertToResponse(List<MovieDTO> movieDTOS) {
        List<MovieResponseRestApi> result = new ArrayList<MovieResponseRestApi>();
        MovieResponseRestApi response;
        for (MovieDTO movie : movieDTOS) {
            response = converterDTOToResponse.convert(movie);
            result.add(response);
        }

        return result;
    }


}
