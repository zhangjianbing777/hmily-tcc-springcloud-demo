
package com.zhangjianbing.story.controller;

import com.zhangjianbing.modul.api.IServiceInventoryApi;
import com.zhangjianbing.modul.dto.InventoryDTO;
import com.zhangjianbing.story.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController implements IServiceInventoryApi {

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Boolean decrease(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.decrease(inventoryDTO);
    }

    @Override
    public Integer findByProductId(@RequestParam("productId") String productId) {
        return inventoryService.findByProductId(productId).getTotalInventory();
    }

    @Override
    public Boolean mockWithTryException(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.mockWithTryException(inventoryDTO);
    }

    @Override
    public Boolean mockWithTryTimeout(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.mockWithTryTimeout(inventoryDTO);
    }

}
