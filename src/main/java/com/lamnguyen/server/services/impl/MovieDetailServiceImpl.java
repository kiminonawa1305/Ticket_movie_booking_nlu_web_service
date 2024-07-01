package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.models.response.MovieDetailResponseRestApi;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieFavoriteService;
import com.lamnguyen.server.services.MovieService;
import com.lamnguyen.server.services.ShowtimeService;
import com.lamnguyen.server.utils.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {
    @Autowired
    public MovieService movieService;
    @Autowired
    public ShowtimeService showtimeService;
    @Autowired
    public MovieFavoriteService movieFavoriteService;

    @Override
    public MovieDetailResponse getMovieDetail(Integer userId, Integer movieId, String date) {
        MovieDTO movie = movieService.findById(movieId);
        List<ShowtimeDTO> showtimes = showtimeService.findShowTimeDTOByMovieId(movieId, DateTimeFormat.generateStartDate(date));
        String key = movie.getIdApi();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(MovieService.URL_API + key, String.class);
        MovieDetailResponseRestApi restApi = new Gson().fromJson(data, MovieDetailResponseRestApi.class);
        MovieDetailResponse result = MovieDetailResponse.builder()
                .id(movie.getId())
                .title(restApi.getTitle())
                .poster(restApi.getPoster())
                .vote(0)
                .avail(showtimes != null && !showtimes.isEmpty())
                .rate(5.0)
                .duration(restApi.getRuntime())
                .description(restApi.getPlot())
                .favourite(movieFavoriteService.getFavoriteMoviesByUserIdAndMovieId(userId, movieId) != null)
                .build();
        result.parseActors(restApi.getActors());
        result.parseDirectors(restApi.getDirector());
        result.parseGenres(restApi.getGenre());
        return result;
    }
}
