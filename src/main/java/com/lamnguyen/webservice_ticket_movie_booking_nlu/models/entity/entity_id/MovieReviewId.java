package com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.entity_id;


import lombok.Data;

@Data
public class MovieReviewId {
    private Long movieId;
    private Integer customerId;

    public MovieReviewId(Long movieId, Integer customerId) {
        this.movieId = movieId;
        this.customerId = customerId;
    }
}
