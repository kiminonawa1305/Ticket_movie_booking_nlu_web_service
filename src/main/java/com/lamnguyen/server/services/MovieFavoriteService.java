package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.MovieFavorite;

import java.util.List;

public interface MovieFavoriteService {
    List<Integer> getFavoriteMoviesByCustomerId(Integer customerId);
}
