package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.dto.PriceManageDTO;
import com.lamnguyen.server.services.PriceManageService;
import com.lamnguyen.server.services.impl.PriceManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/priceManage")
public class PriceManageController {
    @Autowired
    private PriceManageServiceImpl priceBoardService;

    @GetMapping
    public List<PriceManageDTO> getAllPriceInfo() {
        return priceBoardService.getAllPriceInfo();
    }
    @PostMapping("/update")
    public void updatePriceBoard(@RequestBody PriceManageDTO priceManageDTO) {
        priceBoardService.updatePriceBoardByCinemaId(priceManageDTO.getCouple(), priceManageDTO.getSingle(), priceManageDTO.getVip(), priceManageDTO.getCinema_Id());
    }
}
