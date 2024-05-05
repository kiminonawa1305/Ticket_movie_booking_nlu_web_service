package com.lamnguyen.webservice_ticket_movie_booking_nlu.converter;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.ChairDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.CustomerDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieReviewDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Customer;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.MovieReview;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.response.MovieResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterDTOToResponse {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convert(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public MovieResponse convert(MovieDTO movie) {
        return modelMapper.map(movie, MovieResponse.class);
    }

    public MovieReviewDTO convert(MovieReview review) {
        return modelMapper.map(review, MovieReviewDTO.class);
    }

    public ChairDTO convert(Chair chair) {
        return modelMapper.map(chair, ChairDTO.class);
    }
}
