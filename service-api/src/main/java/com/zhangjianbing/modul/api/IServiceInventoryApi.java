package com.zhangjianbing.modul.api;

import com.zhangjianbing.modul.dto.InventoryDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存模块的api
 *
 * @author zhangjianbing
 * time 2019/10/1
 */
@FeignClient(value = "hmily-inventory")
public interface IServiceInventoryApi {

    /**
     * 库存扣减.
     *
     * @param inventoryDTO 实体对象
     * @return true 成功
     */
    @RequestMapping("/inventory/decrease")
    @Hmily
    Boolean decrease(@RequestBody InventoryDTO inventoryDTO);


    /**
     * 获取商品库存.
     *
     * @param productId 商品id
     * @return InventoryDO integer
     */
    @RequestMapping("/inventory/findByProductId")
    Integer findByProductId(@RequestParam("productId") String productId);


    /**
     * 模拟库存扣减异常.
     *
     * @param inventoryDTO 实体对象
     * @return true 成功
     */
    @Hmily
    @RequestMapping("/inventory/mockWithTryException")
    Boolean mockWithTryException(@RequestBody InventoryDTO inventoryDTO);


    /**
     * 模拟库存扣减超时.
     *
     * @param inventoryDTO 实体对象
     * @return true 成功
     */
    @Hmily
    @RequestMapping("/inventory/mockWithTryTimeout")
    Boolean mockWithTryTimeout(@RequestBody InventoryDTO inventoryDTO);

}
