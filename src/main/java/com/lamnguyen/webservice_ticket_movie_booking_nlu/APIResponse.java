package com.lamnguyen.webservice_ticket_movie_booking_nlu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;
}
