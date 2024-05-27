package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailResponseRestApi implements Serializable {
    private String Title, Poster, Genre, Runtime, Actors, Director, Plot;
}
