package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.enums.ChairStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "`chairs`")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`status`", columnDefinition = "ENUM('AVAILABLE', 'SELECTED', 'SOLD')")
    private String status = ChairStatus.AVAILABLE.toString();

    @Column
    private String name;

    @Column(name = "`describe`")
    private String describe;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "chair")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "chair_type_id")
    private ChairType chairType;
}
