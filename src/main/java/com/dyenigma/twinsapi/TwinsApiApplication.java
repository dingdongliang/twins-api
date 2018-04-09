package com.dyenigma.twinsapi;

import com.dyenigma.twinsapi.druid.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@Import(DynamicDataSourceRegister.class)
@MapperScan("com.dyenigma.twinsapi.dao")
@ServletComponentScan
public class TwinsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwinsApiApplication.class, args);
    }
}