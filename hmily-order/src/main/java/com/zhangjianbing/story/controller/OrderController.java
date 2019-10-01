
package com.zhangjianbing.story.controller;

import com.zhangjianbing.modul.api.IServiceOrderApi;
import com.zhangjianbing.story.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OrderController implements IServiceOrderApi {

    @Autowired
    private OrderService orderService;

    public String orderPay(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.orderPay(count, amount);
    }

    public String mockInventoryWithTryException(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockInventoryWithTryException(count, amount);
    }

    public String mockInventoryWithTryTimeout(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.mockInventoryWithTryTimeout(count, amount);
    }

}
