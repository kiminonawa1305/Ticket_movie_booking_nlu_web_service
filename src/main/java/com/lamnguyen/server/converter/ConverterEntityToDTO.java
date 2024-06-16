package com.lamnguyen.server.converter;

import com.lamnguyen.server.models.dto.*;
import com.lamnguyen.server.models.entity.*;
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

    public ChairShowtimeDTO convert(ChairShowTime chairShowTime) {
        return modelMapper.map(chairShowTime, ChairShowtimeDTO.class);
    }

    public MovieReviewDTO convert(MovieReview review) {
        return modelMapper.map(review, MovieReviewDTO.class);
    }

    public ChairDTO convertToChairDTO(Chair chair) {
        return modelMapper.map(chair, ChairDTO.class);
    }
}
