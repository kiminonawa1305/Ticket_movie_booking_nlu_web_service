package com.lamnguyen.server.models.entity;

import com.lamnguyen.server.enums.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleUser role = RoleUser.USER;
    private String phone;

    @Column(name = "`lock`")
    @ColumnDefault("false")
    private boolean lock = false;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private List<MovieReview> movieReviews;

    @OneToMany(mappedBy = "user")
    private List<MovieFavorite> movieFavorites;

    @OneToMany(mappedBy = "user")
    private List<ChairShowTime> chairShowTime;
}
