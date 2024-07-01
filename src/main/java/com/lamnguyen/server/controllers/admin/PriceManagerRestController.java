package com.lamnguyen.server.controllers.admin;

import com.lamnguyen.server.models.dto.PriceManageDTO;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.impl.PriceManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/api/price")
public class PriceManagerRestController {
    @Autowired
    private PriceManageServiceImpl priceBoardService;

    @GetMapping
    public List<PriceManageDTO> getAllPriceInfo() {
        return priceBoardService.getAllPriceInfo();
    }

    @PostMapping("/update")
    public APIResponse<Void> updatePriceBoard(@RequestBody PriceManageDTO priceManageDTO) {
        priceBoardService.updatePriceBoardByCinemaId(priceManageDTO.getCouple(), priceManageDTO.getSingle(), priceManageDTO.getVip(), priceManageDTO.getCinemaId());
        return APIResponse.<Void>builder()
                .status(200)
                .message("Update price board successfully")
                .build();
    }
}
