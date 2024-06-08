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

import java.util.List;

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
    public List<Showtime> findByMovieId(int id) {
        return showtimeRepository.findByAvailIsTrueAndMovie_Id(id);
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
