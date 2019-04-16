
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
     * 用户账户付款.
     *
     * @param accountDO 实体类
     * @return true 成功
     */
    @RequestMapping("/account/payment")
    @Hmily
    Boolean payment(@RequestBody AccountDTO accountDO);


    /**
     * 获取用户账户信息.
     *
     * @param userId 用户id
     * @return AccountDO big decimal
     */
    @RequestMapping("/account/findByUserId")
    BigDecimal findByUserId(@RequestParam("userId") String userId);

}
