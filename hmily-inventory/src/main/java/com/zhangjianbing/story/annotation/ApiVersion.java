package com.zhangjianbing.story.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author 张建兵 Ryan
 * time 2019/4/16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * 版本号
     */
    int[] value();

}
