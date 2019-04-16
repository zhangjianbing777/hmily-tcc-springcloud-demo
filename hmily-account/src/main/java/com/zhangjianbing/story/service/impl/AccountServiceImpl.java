
package com.zhangjianbing.story.service.impl;

import com.zhangjianbing.story.dto.AccountDTO;
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

    /**
     * logger.
     */
    @Autowired
    private AccountMapper accountMapper;


    @Override
    @Hmily(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional
    public boolean payment(AccountDTO accountDTO) {
        System.out.println("============执行try付款接口===============");
        accountMapper.update(accountDTO);
        //内嵌调用
        //inLineService.test();
        int i = 10 / 0;
        return Boolean.TRUE;
    }

    @Override
    public AccountDO findByUserId(final String userId) {
        return accountMapper.findByUserId(userId);
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    public boolean confirm(AccountDTO accountDTO) {
        System.out.println("============执行confirm 付款接口===============");
        final int rows = accountMapper.confirm(accountDTO);
        return Boolean.TRUE;
    }


    /**
     * Cancel boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    public boolean cancel(AccountDTO accountDTO) {
        System.out.println("============执行cancel 付款接口===============");
        final int rows = accountMapper.cancel(accountDTO);
        if (rows != 1) {
            throw new HmilyRuntimeException("取消扣减账户异常！");
        }
        return Boolean.TRUE;
    }
}
