
package com.zhangjianbing.story.service.impl;

import com.zhangjianbing.modul.dto.AccountDTO;
import com.zhangjianbing.story.entity.AccountDO;
import com.zhangjianbing.story.mapper.AccountMapper;
import com.zhangjianbing.story.service.AccountService;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    @Hmily(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean payment(AccountDTO accountDTO) {
        System.out.println("============执行try付款接口===============");
        accountMapper.update(accountDTO);
        return Boolean.TRUE;
    }

    public boolean confirm(AccountDTO accountDTO) {
        System.out.println("============执行confirm 付款接口===============");
        final int rows = accountMapper.confirm(accountDTO);
        return Boolean.TRUE;
    }

    public boolean cancel(AccountDTO accountDTO) {
        System.out.println("============执行cancel 付款接口===============");
        final int rows = accountMapper.cancel(accountDTO);
        if (rows != 1) {
            throw new HmilyRuntimeException("取消扣减账户异常！");
        }
        return Boolean.TRUE;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    @Override
    @Hmily(confirmMethod = "confirmMsg", cancelMethod = "cancelMsg")
    public boolean updateMsg() {
        System.out.println("============执行更新账户信息接口===============");
        return Boolean.TRUE;
    }

    public boolean confirmMsg() {
        System.out.println("============执行 confirmMsg 付款接口===============");
        return Boolean.TRUE;
    }

    public boolean cancelMsg() {
        System.out.println("============执行 cancelMsg 付款接口===============");
        return Boolean.TRUE;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

}
