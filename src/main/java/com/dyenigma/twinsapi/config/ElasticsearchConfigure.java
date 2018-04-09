package com.dyenigma.twinsapi.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : elastic 访问配置,如果遇到日志报错，添加log4j-to-slf4j包
 * @Author : dingdongliang
 * @Date : 2018/3/30 15:38
 */
@Configuration
@Slf4j
public class ElasticsearchConfigure implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    private TransportClient transportClient;
    private PreBuiltTransportClient preBuiltTransportClient;

    @Override
    public void destroy() throws Exception {
        try {
            log.info("Closing elasticSearch client");
            if (transportClient != null) {
                transportClient.close();
            }
        } catch (final Exception e) {
            log.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return transportClient;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    /**
     * @return void
     * @Description: 这个方法根据配置文件内容进行自定义编码
     * @author dingdongliang
     * @date 2018/3/30 16:05
     */
    protected void buildClient() {
        try {
            preBuiltTransportClient = new PreBuiltTransportClient(settings());

            String[] nodes = clusterNodes.split(";");
            for (String str : nodes) {
                String[] InetSocket = str.split(":");
                String address = InetSocket[0];
                Integer port = Integer.valueOf(InetSocket[1]);
                transportClient = preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress
                        .getByName(address), port));
            }
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 初始化默认的client
     */
    private Settings settings() {

        Settings settings = Settings.builder().put("cluster.name", "my-application").build();
        return settings;
    }
}

