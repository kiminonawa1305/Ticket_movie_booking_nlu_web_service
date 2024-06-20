package com.lamnguyen.server.services.impl;

import com.google.firebase.database.FirebaseDatabase;
import com.lamnguyen.server.converter.ConverterEntityToDTO;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.entity.Customer;
import com.lamnguyen.server.repositories.ChairRepository;
import com.lamnguyen.server.requests.ChairUpdateRequest;
import com.lamnguyen.server.services.ChairService;
import com.lamnguyen.server.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChairServiceImpl implements ChairService {
    @Autowired
    FirebaseDatabase firebaseDatabase;
    @Autowired
    ShowtimeService showtimeService;
    @Autowired
    ChairRepository chairRepository;
    @Autowired
    ConverterEntityToDTO converterEntityToDTO;


    private List<ChairDTO> convert(List<Chair> chairs) {
        List<ChairDTO> result = new ArrayList<>();
        ChairDTO dto;
        for (Chair chair : chairs) {
            dto = convertToChairDTO(chair);
            result.add(dto);
        }

        return result;
    }

    private ChairDTO convertToChairDTO(Chair chair) {
        return converterEntityToDTO.convertToChairDTO(chair);
    }
}
