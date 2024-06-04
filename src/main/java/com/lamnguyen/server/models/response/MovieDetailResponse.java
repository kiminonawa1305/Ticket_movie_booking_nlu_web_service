package com.lamnguyen.server.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailResponse {
    private String title, poster, duration, description;
    private Integer id, vote;
    private Double rate;
    private String[] actors, directors, genres;

    public void parseActors(String actors) {
        this.actors = actors.split(", ");
    }

    public void parseDirectors(String directors) {
        this.directors = directors.split(", ");
    }

    public void parseGenres(String genres) {
        this.genres = genres.split(", ");
    }
}
