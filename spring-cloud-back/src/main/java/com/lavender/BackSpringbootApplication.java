package com.lavender;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableScheduling
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.lavender.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class BackSpringbootApplication {

    public static void main(String[] args) {

        SpringApplication.run (BackSpringbootApplication.class, args);

    }

}

