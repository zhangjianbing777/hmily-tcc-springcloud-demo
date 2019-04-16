package com.zhangjianbing.story;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 张建兵 Ryan
 * time 2019/4/16
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zhangjianbing.story.mapper")
public class ApplicationInventory {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationInventory.class, args);
    }

}
