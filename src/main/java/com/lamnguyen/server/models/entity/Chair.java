package com.lamnguyen.server.models.entity;

import com.lamnguyen.server.enums.ChairType;
import jakarta.persistence.*;
import lombok.*;

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
    @Column
    private String name;

    @Column(name = "`type`", columnDefinition = "ENUM('SINGLE', 'COUPLE', 'VIP')")
    @Enumerated(EnumType.STRING)
    private ChairType type;

    @OneToMany(mappedBy = "chair")
    private List<ChairShowTime> chairShowTimes;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
