package com.dyenigma.twinsapi.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : MongoDB配置类，注意这里是访问经过加密的数据库
 * @Author : dingdongliang
 * @Date : 2018/3/29 14:00
 */
@Getter
@Setter
public abstract class AbstractMongoConfigure {
    private String username, database;
    private String[] host;
    private char[] password;
    private int port;

    /**
     * @return org.springframework.data.mongodb.MongoDbFactory
     * @Description: 配置多节点数据源工厂，如果每个节点的port不同，就写成类似127.0.0.1:8080形式，然后再拆分即可
     * @author dingdongliang
     * @date 2018/3/29 15:18
     */
    public MongoDbFactory mongoDbFactory() throws Exception {

        List<ServerAddress> serverAddressList = new ArrayList<>();

        for (String str : host) {
            ServerAddress serverAddress = new ServerAddress(str, port);
            serverAddressList.add(serverAddress);
        }

        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList.add(MongoCredential.createCredential(username, database, password));
        return new SimpleMongoDbFactory(new MongoClient(serverAddressList, mongoCredentialList), database);
    }

    abstract public MongoTemplate getMongoTemplate() throws Exception;
}

