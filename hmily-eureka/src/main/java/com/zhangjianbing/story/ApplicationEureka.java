package com.zhangjianbing.story;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 张建兵 Ryan
 * time 2019/4/16
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka.class, args);
    }

}
