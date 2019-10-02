package com.zhangjianbing.edge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhangjianbing
 * time 2019/10/1
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.zhangjianbing")
@ComponentScan(basePackages = "com.zhangjianbing")
public class EdgeServiceBootStrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EdgeServiceBootStrap.class).run(args);
    }

}
