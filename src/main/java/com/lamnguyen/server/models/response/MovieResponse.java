package com.lamnguyen.server.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse implements Serializable {
    private String title, poster, genre, duration;
    private Integer id, vote;
    private Double rate;
    private boolean favourite;
}
