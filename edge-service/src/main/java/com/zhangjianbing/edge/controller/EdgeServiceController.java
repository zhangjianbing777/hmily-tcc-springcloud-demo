package com.zhangjianbing.edge.controller;

import com.zhangjianbing.modul.api.IServiceOrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author zhangjianbing
 * time 2019/10/1
 */
@RestController
@RequestMapping(value = "/edgeService")
public class EdgeServiceController {

    @Autowired
    private IServiceOrderApi serviceOrderApi;

    @PostMapping(value = "/buy")
    public String buy(@RequestParam(value = "count") Integer count, @RequestParam(value = "amount") BigDecimal amount) {
        String pay = serviceOrderApi.orderPay(count, amount);
        return pay;
    }

    @GetMapping(value = "/testTcc")
    public String testTcc() {
        // 远程接口
        String tcc = serviceOrderApi.testTcc();
        return tcc;
    }

}
