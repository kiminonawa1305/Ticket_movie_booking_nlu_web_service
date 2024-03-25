package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.entity_id.MovieReviewId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movie_reviews")
@IdClass(MovieReviewId.class)
public class MovieReview {
    @Id
    @Column(name = "movie_id")
    private Integer movieId;
    @Id
    @Column(name = "customer_id")
    private Integer customerId;
    private Double star;
    private String content;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
