package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.entity.Chair;
import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.repositories.ShowtimeRepository;
import com.lamnguyen.server.services.ShowtimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Showtime findById(int id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Chair findChairById(Integer showtimeId, Integer chairId) {
        Showtime showtime = findById(showtimeId);
        if (showtime != null) {
            return showtime.getRoom().getChairs().stream().filter(chair -> chair.getId().equals(chairId)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public PriceBoardDTO getPriceBoard(Integer showtimeId) {
        PriceBoard priceBoard = showtimeRepository.findPriceBoard(showtimeId);
        return convert(priceBoard);
    }

    private PriceBoardDTO convert(PriceBoard priceBoard) {
        return modelMapper.map(priceBoard, PriceBoardDTO.class);
    }
}
