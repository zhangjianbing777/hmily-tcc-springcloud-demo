package com.zhangjianbing.story.controller;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张建兵 Ryan
 * time 2019/4/16
 */
@RestController
@RequestMapping(value = "/test")
public class Test01Controller {

    @Hmily(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    @RequestMapping(value = "/getMsg")
    public String getMsg(String str) {
        return str;
    }

    public void confirmMethod(String str) {
        System.out.println("Hmily执行了 confirmMethod 方法：" + str);
    }

    public void cancelMethod(String str) {
        System.out.println("Hmily执行了 cancelMethod 方法：" + str);
    }

    @Hmily(confirmMethod = "confirmMethod1", cancelMethod = "cancelMethod1")
    @RequestMapping(value = "/getMsg1")
    public String getMsg1(String str) {
        int i = 10 / 0;
        return str;
    }

    public void confirmMethod1(String str) {
        System.out.println("Hmily执行了 confirmMethod1 方法：" + str);
    }

    public void cancelMethod1(String str) {
        System.out.println("Hmily执行了 cancelMethod1 方法：" + str);
    }

}
