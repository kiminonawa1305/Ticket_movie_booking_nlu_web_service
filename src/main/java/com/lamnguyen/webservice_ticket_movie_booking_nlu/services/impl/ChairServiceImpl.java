package com.lamnguyen.webservice_ticket_movie_booking_nlu.services.impl;

import com.google.firebase.database.FirebaseDatabase;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.converter.ConverterEntityToDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.ChairDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.dto.MovieDTO;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Chair;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Movie;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.models.entity.Showtime;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.repositories.ChairRepository;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.ChairService;
import com.lamnguyen.webservice_ticket_movie_booking_nlu.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChairServiceImpl implements ChairService {
    @Autowired
    FirebaseDatabase database;
    @Autowired
    ShowtimeService showtimeService;
    @Autowired
    ChairRepository chairRepository;
    @Autowired
    ConverterEntityToDTO converterEntityToDTO;


    @Override
    public String createKeyChairStatus(Integer showtimeId) {
        String key = database.getReference(URL).child(String.valueOf(showtimeId)).getKey();
        if (key != null)
            return URL + "/" + key;
        database.getReference().child(URL).child(String.valueOf(showtimeId)).setValueAsync(null);
        return URL + "/" + showtimeId;
    }

    @Override
    public ChairDTO updateChairStatus(Integer showtimeId, Integer chairId, String status) {
        String key = database.getReference(URL).child(String.valueOf(showtimeId)).getKey();
        if (key == null) {
            createKeyChairStatus(showtimeId);
            key = showtimeId.toString();
        }

        Chair chair = showtimeService.findChairById(showtimeId, chairId);
        if (chair == null)
            return null;
        int result = chairRepository.updateById(chairId, status);
        if (result < 1)
            return null;
        ChairDTO dto = convert(chair);
        dto.setStatus(status);
        database.getReference().child(URL).child(key).setValueAsync(dto);
        return dto;
    }

    public void disconnectKeyChairStatus() {
        database.getReference().child("").removeValueAsync();
    }

    private List<ChairDTO> convert(List<Chair> chairs) {
        List<ChairDTO> result = new ArrayList<>();
        ChairDTO dto;
        for (Chair chair : chairs) {
            dto = convert(chair);
            result.add(dto);
        }

        return result;
    }

    private ChairDTO convert(Chair chair) {
        return converterEntityToDTO.convert(chair);
    }
}
