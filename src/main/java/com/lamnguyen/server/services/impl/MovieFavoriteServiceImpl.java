package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.entity.MovieFavorite;
import com.lamnguyen.server.models.response.MovieResponse;
import com.lamnguyen.server.models.response.MovieResponseRestApi;
import com.lamnguyen.server.repositories.MovieFavoriteRepository;
import com.lamnguyen.server.services.MovieFavoriteService;
import com.lamnguyen.server.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import javax.persistence.EntityManager;
//import javax.persistence.Query;
import java.util.List;

@Service
public class MovieFavoriteServiceImpl implements MovieFavoriteService {
    @Autowired
    private MovieFavoriteRepository movieFavoriteRepository;
    @Autowired
    private MovieService movieService;

    @Override
    public List<MovieResponse> getFavoriteMoviesByUserId(Integer userId) {
        List<MovieFavorite> movieFavorites = movieFavoriteRepository.findByUser_Id(userId);

        RestTemplate restTemplate = new RestTemplate();
        return movieFavorites.stream().map(movieFavorite -> {
            Movie movie = movieFavorite.getMovie();
            MovieResponseRestApi movieResponseRestApi = movieService.getMovieResponseRestApi(movie.getIdApi());
            return movieService.convertMovieResponseRestApiToMovieResponse(movie, movieResponseRestApi);
        }).toList();
    }
}
