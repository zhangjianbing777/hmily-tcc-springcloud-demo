package com.zhangjianbing.story;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 张建兵 Ryan
 * time 2019/4/16
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.zhangjianbing.story.mapper")
public class ApplicationOrder {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationOrder.class, args);
    }

}
