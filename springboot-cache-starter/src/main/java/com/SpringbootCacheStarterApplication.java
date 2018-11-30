package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.joker.cache"})
@SpringBootApplication
public class SpringbootCacheStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheStarterApplication.class, args);
    }

}