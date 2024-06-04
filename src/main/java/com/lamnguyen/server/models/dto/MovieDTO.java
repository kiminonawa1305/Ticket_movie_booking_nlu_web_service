package com.lamnguyen.server.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDTO implements Serializable {
    private Integer id;
    private String idApi;
}
