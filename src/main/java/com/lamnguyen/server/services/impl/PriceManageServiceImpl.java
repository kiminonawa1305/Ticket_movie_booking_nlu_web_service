package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.dto.PriceManageDTO;
import com.lamnguyen.server.repositories.PriceManageRepository;
import com.lamnguyen.server.services.PriceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceManageServiceImpl implements PriceManageService {
    @Autowired
    private PriceManageRepository priceManageRepository;

    public List<PriceManageDTO> getAllPriceInfo() {
        return priceManageRepository.findAllPriceInfo();
    }

}
