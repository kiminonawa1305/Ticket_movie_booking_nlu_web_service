package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.server.converter.ConverterDTOToResponse;
import com.lamnguyen.server.converter.ConverterEntityToDTO;
import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.entity.MovieReview;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.models.response.MovieResponseRestApi;
import com.lamnguyen.server.repositories.MovieRepository;
import com.lamnguyen.server.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movie -> convertMovieResponseRestApiToMovieResponse(movie, getMovieResponseRestApi(movie.getIdApi()))).toList();
    }

    @Override
    public MovieDTO findById(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) return null;
        return convertToDTO(movie);
    }


    @Override
    public List<MovieResponse> getMovieHasShowtime(LocalDateTime date) {
        List<Movie> movies = movieRepository.getMovieHasShowtime(date, LocalDateTime.of(date.toLocalDate(), LocalTime.MAX));
        return movies.stream().map(movie -> convertMovieResponseRestApiToMovieResponse(movie, getMovieResponseRestApi(movie.getIdApi()))).toList();
    }

    @Override
    public MovieResponseRestApi getMovieResponseRestApi(String idApi) {
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + idApi, String.class);
        return new Gson().fromJson(data, MovieResponseRestApi.class);
    }

    @Override
    public MovieResponse convertMovieResponseRestApiToMovieResponse(Movie movie, MovieResponseRestApi movieResponseRestApi) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movieResponseRestApi.getTitle())
                .poster(movieResponseRestApi.getPoster())
                .genre(movieResponseRestApi.getGenre())
                .duration(movieResponseRestApi.getRuntime())
                .vote(movie.getMovieReviews().size())
                .rate(rating(movie))
                .build();
    }


    private double rating(Movie movie) {
        List<MovieReview> movieReviews = movie.getMovieReviews();
        int countStar = movieReviews.stream().map(MovieReview::getStar).reduce(0, Integer::sum);
        return movieReviews.isEmpty() ? 5.0 : countStar / (double) movieReviews.size();
    }

    @Override
    public Movie insert(Movie movie) {
        return movieRepository.save(movie);
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
