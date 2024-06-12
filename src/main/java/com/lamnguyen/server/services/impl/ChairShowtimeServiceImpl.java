package com.lamnguyen.server.services.impl;

import com.google.firebase.database.FirebaseDatabase;
import com.lamnguyen.server.converter.ConverterEntityToDTO;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.models.dto.ChairDTO;
import com.lamnguyen.server.models.dto.ChairShowtimeDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.entity.ChairShowTime;
import com.lamnguyen.server.models.entity.Customer;
import com.lamnguyen.server.repositories.ChairRepository;
import com.lamnguyen.server.repositories.ChairShowtimeRepository;
import com.lamnguyen.server.requests.ChairUpdateRequest;
import com.lamnguyen.server.services.ChairService;
import com.lamnguyen.server.services.ChairShowtimeService;
import com.lamnguyen.server.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChairShowtimeServiceImpl implements ChairShowtimeService {
    @Autowired
    FirebaseDatabase firebaseDatabase;
    @Autowired
    ShowtimeService showtimeService;
    @Autowired
    ChairShowtimeRepository chairShowtimeRepository;
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
    public List<ChairShowtimeDTO> getChairShowtime(Integer showtimeId) {
        List<ChairShowTime> chairs = showtimeService.findById(showtimeId).getChairShowTimes();
        return convert(chairs);
    }

    @Override
    public ChairShowtimeDTO updateChairStatus(ChairUpdateRequest chairUpdateRequest) {
        ChairShowTime chairShowTime = chairShowtimeRepository.findById(chairUpdateRequest.getChairId()).orElse(null);
        if (chairShowTime == null ||
                chairShowTime.getStatus().equals(ChairStatus.SOLD) ||
                chairShowTime.getCustomer() != null && chairShowTime.getStatus().equals(ChairStatus.SELECTED) && !chairShowTime.getCustomer().getId().equals(chairUpdateRequest.getUserId()))
            return null;

        chairShowTime.setStatus(chairUpdateRequest.getStatus());
        chairShowTime.setCustomer(Customer.builder().id(chairUpdateRequest.getUserId()).build());
        ChairShowtimeDTO chairUpdateDTO = convertToChairDTO(chairShowTime);
        chairUpdateDTO.setUserId(chairUpdateRequest.getUserId());
        chairUpdateDTO.setName(chairShowTime.getChair().getName());
        chairUpdateDTO.setType(chairShowTime.getChair().getType());
        firebaseDatabase.getReference().child(chairUpdateRequest.getUuid()).setValueAsync(chairUpdateDTO);
        chairShowtimeRepository.saveAndFlush(chairShowTime);
        return convertToChairDTO(chairShowTime);
    }

    public void disconnectKeyChairStatus() {
        firebaseDatabase.getReference().child("").removeValueAsync();
    }

    private List<ChairShowtimeDTO> convert(List<ChairShowTime> chairs) {
        List<ChairShowtimeDTO> result = new ArrayList<>();
        ChairShowtimeDTO dto;
        for (ChairShowTime chair : chairs) {
            dto = convertToChairDTO(chair);
            dto.setName(chair.getChair().getName());
            dto.setType(chair.getChair().getType());
            if (chair.getCustomer() != null)
                dto.setUserId(chair.getCustomer().getId());
            dto.setType(chair.getChair().getType());
            result.add(dto);
        }

        return result;
    }

    private ChairShowtimeDTO convertToChairDTO(ChairShowTime chair) {
        return converterEntityToDTO.convert(chair);
    }
}
