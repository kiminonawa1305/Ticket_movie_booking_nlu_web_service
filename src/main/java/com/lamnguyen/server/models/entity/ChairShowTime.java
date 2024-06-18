package com.lamnguyen.server.models.entity;

import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.enums.ChairType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "`chair_showtimes`")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChairShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`status`", columnDefinition = "ENUM('AVAILABLE', 'SELECTED', 'SOLD')")
    @Enumerated(EnumType.STRING)
    private ChairStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chair_id")
    private Chair chair;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @OneToMany(mappedBy = "chairShowTime")
    private List<Ticket> tickets;
}
