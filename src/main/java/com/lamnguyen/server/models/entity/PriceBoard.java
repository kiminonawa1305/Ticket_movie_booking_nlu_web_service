package com.lamnguyen.server.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`price_boards`")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double single;
    private Double couple;
    private Double vip;

    @OneToOne
    @MapKey
    private Cinema cinema;
}
