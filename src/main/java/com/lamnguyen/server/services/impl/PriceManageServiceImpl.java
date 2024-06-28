package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.dto.PriceManageDTO;
import com.lamnguyen.server.repositories.PriceManageRepository;
import com.lamnguyen.server.services.PriceManageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceManageServiceImpl implements PriceManageService {
    @Autowired
    private PriceManageRepository priceManageRepository;

    @Override
    public List<PriceManageDTO> getAllPriceInfo() {
        return priceManageRepository.findAllPriceInfo();
    }

    @Override
    @Transactional
    public void updatePriceBoardByCinemaId(Double couple, Double single, Double vip, int cinemaId) {
        priceManageRepository.updatePriceBoardByCinemaId(couple, single, vip, cinemaId);
    }

}
