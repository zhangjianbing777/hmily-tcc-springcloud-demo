package com.zhangjianbing.modul.api;

import com.zhangjianbing.modul.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账户模块的api
 *
 * @author zhangjianbing
 * time 2019/10/1
 */
@FeignClient(value = "hmily-account")
public interface IServiceAccountApi {

    /**
     * 支付接口
     */
    @RequestMapping("/account/payment")
    @Hmily
    Boolean payment(@RequestBody AccountDTO accountDO);

    /**
     * 更新账户信息
     */
    @RequestMapping("/account/updateMsg")
    @Hmily
    Boolean updateMsg();

}
