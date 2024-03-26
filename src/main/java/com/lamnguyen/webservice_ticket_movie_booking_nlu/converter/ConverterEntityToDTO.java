package com.lamnguyen.webservice_ticket_movie_booking_nlu.converter;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.CustomerDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieReviewDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Customer;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.MovieReview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToDTO {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convert(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public MovieDTO convert(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieReviewDTO convert(MovieReview review) {
        return modelMapper.map(review, MovieReviewDTO.class);
    }
}
