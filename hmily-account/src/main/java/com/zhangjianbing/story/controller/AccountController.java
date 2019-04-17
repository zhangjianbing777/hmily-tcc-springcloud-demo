package com.zhangjianbing.story.controller;

import com.zhangjianbing.story.dto.AccountDTO;
import com.zhangjianbing.story.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Account项目接口 1
     */
    @RequestMapping("/payment")
    public Boolean save(@RequestBody AccountDTO accountDO) {
        return accountService.payment(accountDO);
    }

    /**
     * Account项目接口 2
     */
    @RequestMapping("/updateMsg")
    public Boolean updateMsg() {
        return accountService.updateMsg();
    }

}
