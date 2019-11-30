package com.zhangjianbing.modul.api;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 订单模块的api
 *
 * @author zhangjianbing
 * time 2019/10/1
 */
@FeignClient(value = "hmily-order")
public interface IServiceOrderApi {

    @PostMapping("/order/orderPay")
    @Hmily
    String orderPay(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount);

    @PostMapping("/order/mockInventoryWithTryException")
    String mockInventoryWithTryException(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount);

    @PostMapping(value = "/order/mockInventoryWithTryTimeout")
    String mockInventoryWithTryTimeout(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount);

}
