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


    @Override
    public String createKeyChairStatus(Integer showtimeId) {
        String key = firebaseDatabase.getReference(URL).child(String.valueOf(showtimeId)).getKey();
        if (key != null)
            return URL + "/" + key;
        firebaseDatabase.getReference().child(URL).child(String.valueOf(showtimeId)).setValueAsync(null);
        return URL + "/" + showtimeId;
    }

    @Override
    public List<ChairDTO> getChairStatus(Integer showtimeId) {
        List<Chair> chairs = showtimeService.findById(showtimeId).getRoom().getChairs();
        return convert(chairs);
    }

    @Override
    public ChairDTO updateChairStatus(ChairUpdateRequest chairUpdateRequest) {
        Chair chair = chairRepository.findById(chairUpdateRequest.getChairId()).orElse(null);
        if (chair == null ||
                chair.getStatus().equals(ChairStatus.SOLD) ||
                chair.getCustomer() != null && chair.getStatus().equals(ChairStatus.SELECTED) && !chair.getCustomer().getId().equals(chairUpdateRequest.getUserId()))
            return null;

        chair.setStatus(chairUpdateRequest.getStatus());
        chair.setCustomer(Customer.builder().id(chairUpdateRequest.getUserId()).build());
        ChairDTO chairUpdateDTO = convertToChairDTO(chair);
        firebaseDatabase.getReference().child(chairUpdateRequest.getUuid()).setValueAsync(chairUpdateDTO);
        chairRepository.saveAndFlush(chair);
        return convertToChairDTO(chair);
    }

    public void disconnectKeyChairStatus() {
        firebaseDatabase.getReference().child("").removeValueAsync();
    }

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
        ChairDTO chairDTO = converterEntityToDTO.convertToChairDTO(chair);
        if (chair.getCustomer() != null)
            chairDTO.setUserId(chair.getCustomer().getId());
        return chairDTO;
    }
}
