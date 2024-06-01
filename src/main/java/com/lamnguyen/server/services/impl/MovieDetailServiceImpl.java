package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.models.response.MovieDetailResponseRestApi;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {
    @Autowired
    private MovieService movieService;

    @Override
    public MovieDetailResponse getMovieDetail(Integer id) {
        MovieDTO movie = movieService.findById(id);
        String key = movie.getIdApi();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + movie.getIdApi(), String.class);
        MovieDetailResponseRestApi restApi = new Gson().fromJson(data, MovieDetailResponseRestApi.class);
        MovieDetailResponse result = MovieDetailResponse.builder()
                .id(movie.getId())
                .title(restApi.getTitle())
                .poster(restApi.getPoster())
                .vote(0)
                .rate(5.0)
                .duration(restApi.getRuntime())
                .description(restApi.getPlot())
                .build();
        result.parseActors(restApi.getActors());
        result.parseDirectors(restApi.getDirector());
        result.parseGenres(restApi.getGenre());
        return result;
    }
}