package com.dyenigma.twinsapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : MongoDB次数据库
 * @Author : dingdongliang
 * @Date : 2018/3/29 14:04
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.dyenigma.twinsapi.service"}, mongoTemplateRef = "slaveMongoTemplate")
@ConfigurationProperties(prefix = "spring.data.mongodb.slave")
public class SlaveMongoConfigure extends AbstractMongoConfigure {
    @Primary
    @Override
    @Bean(name = "slaveMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}