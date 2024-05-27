package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Table(name = "cinemas")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "ENUM('Cinestar Sinh Viên (Bình Dương)', 'Gigamall Thủ Đức', 'Galaxy Co.opXtra Linh Trung', 'Co.opmart Xa Lộ Hà Nội')")
    @ColumnDefault("'Cinestar Sinh Viên (Bình Dương)'")
    private String name;
    @OneToMany(mappedBy = "cinema")
    private List<Room> rooms;
}
