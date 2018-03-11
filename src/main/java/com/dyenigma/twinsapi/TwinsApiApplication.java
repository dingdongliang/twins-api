package com.dyenigma.twinsapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dyenigma.twinsapi.dao")
public class TwinsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwinsApiApplication.class, args);
    }
}