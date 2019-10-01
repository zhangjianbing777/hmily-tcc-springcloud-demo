
package com.zhangjianbing.story.service;


import com.zhangjianbing.modul.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;

public interface AccountService {

    /**
     * 扣款支付.
     *
     * @param accountDTO 参数dto
     * @return true
     */
    @Hmily
    boolean payment(AccountDTO accountDTO);

    @Hmily
    boolean updateMsg();

}
