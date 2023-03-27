package com.lavender;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.lavender.mapper")
@EnableDiscoveryClient
public class BackSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run (BackSpringbootApplication.class, args);
    }



}

