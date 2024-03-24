package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie_reviews")
@Data
public class MovieReview {
    @Id
    @Column(name = "movie_id")
    private Long movieId;
    @Id
    @Column(name = "customer_id")
    private Integer customerId;
    private Double star;
    private String content;
    private LocalDateTime date;

    @ManyToOne
    @MapsId
    private Movie movie;

    @ManyToOne
    @MapsId
    private Customer customer;
}
