
package com.zhangjianbing.story.client;

import com.zhangjianbing.story.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "hmily-account")
public interface AccountClient {

    /**
     * Account项目接口 1
     */
    @RequestMapping("/account/payment")
    @Hmily
    Boolean payment(@RequestBody AccountDTO accountDO);

    /**
     * Account项目接口 2
     */
    @RequestMapping("/account/updateMsg")
    @Hmily
    Boolean updateMsg();



    /**
     * 获取用户账户信息.
     *
     * @param userId 用户id
     * @return AccountDO big decimal
     */
    @RequestMapping("/account/findByUserId")
    BigDecimal findByUserId(@RequestParam("userId") String userId);

}
