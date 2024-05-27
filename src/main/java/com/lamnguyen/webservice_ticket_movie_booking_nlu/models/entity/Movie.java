package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_api")
    private String idApi;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Showtime> showtimes;

    @OneToMany(mappedBy = "movie")
    private List<MovieReview> movieReviews;

    @OneToMany(mappedBy = "movie")
    private List<MovieFavorite> movieFavorites;
}
