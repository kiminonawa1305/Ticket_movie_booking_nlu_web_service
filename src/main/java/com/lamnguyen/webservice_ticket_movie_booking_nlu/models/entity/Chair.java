package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chairs")
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`describe`")
    private String describe;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ChairStatus status;

    @OneToMany(mappedBy = "chair")
    private List<Ticket> tickets;
}
