package com.zhangjianbing.story.controller;

import com.zhangjianbing.modul.api.IServiceAccountApi;
import com.zhangjianbing.modul.dto.AccountDTO;
import com.zhangjianbing.story.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController implements IServiceAccountApi {

    @Autowired
    private AccountService accountService;

    /**
     * 支付接口
     * @param accountDO 支付实体
     * @return Boolean
     */
    @Override
    public Boolean payment(@RequestBody AccountDTO accountDO) {
        return accountService.payment(accountDO);
    }

    /**
     * 更新账户信息
     */
    @Override
    public Boolean updateMsg() {
        return accountService.updateMsg();
    }

}
