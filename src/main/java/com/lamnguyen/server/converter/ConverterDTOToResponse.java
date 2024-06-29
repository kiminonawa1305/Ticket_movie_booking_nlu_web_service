package com.lamnguyen.server.converter;

import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.models.dto.UserDTO;
import com.lamnguyen.server.models.dto.MovieDTO;
import com.lamnguyen.server.models.dto.MovieReviewDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.entity.User;
import com.lamnguyen.server.models.entity.MovieReview;
import com.lamnguyen.server.models.response.MovieResponseRestApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterDTOToResponse {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convert(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public MovieResponseRestApi convert(MovieDTO movie) {
        return modelMapper.map(movie, MovieResponseRestApi.class);
    }

    public MovieReviewDTO convert(MovieReview review) {
        return modelMapper.map(review, MovieReviewDTO.class);
    }

    public ChairDTO convert(Chair chair) {
        return modelMapper.map(chair, ChairDTO.class);
    }
}
