package com.zhangjianbing.story;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 张建兵 Ryan
 * time 2019/4/16
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.zhangjianbing")
@MapperScan("com.zhangjianbing.story.mapper")
@ComponentScan(basePackages = "com.zhangjianbing")
public class ApplicationOrder {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationOrder.class, args);
    }

}
