package com.lamnguyen.webservice_ticket_movie_booking_nlu.converter;

import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertEntityToDTO {
    @Autowired
    private ModelMapper modelMapper;
}
