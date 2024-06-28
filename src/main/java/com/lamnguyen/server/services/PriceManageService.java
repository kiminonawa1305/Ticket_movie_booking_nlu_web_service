package com.lamnguyen.server.services;

import com.lamnguyen.server.models.dto.PriceManageDTO;

import java.util.List;

public interface PriceManageService {
    List<PriceManageDTO> getAllPriceInfo();
    void updatePriceBoardByCinemaId(Double couple, Double single, Double vip, int cinemaId);
}
