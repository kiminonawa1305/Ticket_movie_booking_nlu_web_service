package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "id_api")
    private String idApi;

    @Lob
    private String poster;

    @Column(name = "run_time")
    private Integer runTime;

    private Double rate;

    private String genre;

    @Column(name = "votes")
    private Integer vote;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Showtime> showtimes;

    @OneToMany(mappedBy = "movie")
    private List<MovieReview> movieReviews;
}
