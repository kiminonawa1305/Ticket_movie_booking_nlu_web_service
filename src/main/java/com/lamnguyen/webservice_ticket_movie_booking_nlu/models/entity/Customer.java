package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String avatar;
    private String background;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "customer")
    private List<MovieReview> movieReviews;
}
