package com.dyenigma.twinsapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : MongoDB主数据库
 * @Author : dingdongliang
 * @Date : 2018/3/29 14:03
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.dyenigma.twinsapi.service"}, mongoTemplateRef = "masterMongoTemplate")
@ConfigurationProperties(prefix = "spring.data.mongodb.master")
public class MasterMongoConfigure extends AbstractMongoConfigure {
    @Override
    @Bean(name = "masterMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}