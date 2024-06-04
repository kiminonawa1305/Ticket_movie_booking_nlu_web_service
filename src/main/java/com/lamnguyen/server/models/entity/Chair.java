package com.lamnguyen.server.models.entity;

import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.enums.ChairType;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @Column(name = "`status`", columnDefinition = "ENUM('AVAILABLE', 'SELECTED', 'SOLD')")
    @Enumerated(EnumType.STRING)
    private ChairStatus status;

    @Column(name = "`type`", columnDefinition = "ENUM('SINGLE', 'COUPLE', 'VIP')")
    @Enumerated(EnumType.STRING)
    private ChairType type;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "chair")
    private List<Ticket> tickets;
}
