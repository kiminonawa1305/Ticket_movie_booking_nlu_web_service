package com.lamnguyen.server.services;

import com.lamnguyen.server.models.response.MovieDetailResponse;

public interface MovieDetailService {
    MovieDetailResponse getMovieDetail(Integer userId, Integer id, String date);
}
