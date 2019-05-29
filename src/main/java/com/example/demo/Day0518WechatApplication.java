package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
@EnableFeignClients
@EnableScheduling
public class Day0518WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day0518WechatApplication.class, args);
    }

}
