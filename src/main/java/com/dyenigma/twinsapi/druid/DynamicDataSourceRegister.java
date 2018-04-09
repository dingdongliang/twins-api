package com.dyenigma.twinsapi.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.dyenigma.twinsapi.core.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * twins/com.dyenigma.twinsapi.druid
 *
 * @Description : 动态数据源注册,启动动态数据源要在启动类中添加@Import(DynamicDataSourceRegister.class)注解
 * @Author : dingdongliang
 * @Date : 2018/4/2 10:45
 * P.S. 注意获取属性时，getSubProperties 和 getProperty的区别，1.5.4版本前者不能识别属性占位符
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;

    /**
     * 如配置文件中未指定数据源类型，使用该默认值
     */
    private static final Object DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";

    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        log.info("Dynamic DataSource Registry");
    }

    /**
     * 创建DataSource
     * param dsMap
     * return
     */
    public DataSource buildDataSource(Map<String, Object> dsMap) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dsMap.get("url").toString());
        dataSource.setUsername(dsMap.get("username").toString());
        dataSource.setPassword(dsMap.get("password").toString());
        dataSource.setDriverClassName(dsMap.get("driver-class-name").toString());
        dataSource.setConnectionProperties(dsMap.get("connection-properties").toString());

        try {
            dataSource.setFilters(dsMap.get("filters").toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;

    }

    /**
     * 加载多数据源配置
     * param env
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }


    /**
     * 初始化主数据源,这里可以使用配置文件的占位符
     * param env
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.druid.master.");
        Map<String, Object> dsMap = new HashMap<>(8);
        dsMap.put("type", propertyResolver.getProperty("type"));
        dsMap.put("driver-class-name", propertyResolver.getProperty("driver-class-name"));
        dsMap.put("url", propertyResolver.getProperty("url"));
        dsMap.put("username", propertyResolver.getProperty("username"));
        dsMap.put("password", propertyResolver.getProperty("password"));
        dsMap.put("filters", propertyResolver.getProperty("filters"));
        dsMap.put("connection-properties", propertyResolver.getProperty("connection-properties"));

        defaultDataSource = buildDataSource(dsMap);

        dataBinder(defaultDataSource, env);

        log.info("Druid DataSource init > 主数据源");
    }


    /**
     * 初始化更多数据源
     * param env
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.druid.");
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(SystemConstant.COMMA)) {
            Map<String, Object> dsMap = new HashMap<>(8);
            dsMap.put("type", propertyResolver.getProperty(dsPrefix + ".type"));
            dsMap.put("driver-class-name", propertyResolver.getProperty(dsPrefix + ".driver-class-name"));
            dsMap.put("url", propertyResolver.getProperty(dsPrefix + ".url"));
            dsMap.put("username", propertyResolver.getProperty(dsPrefix + ".username"));
            dsMap.put("password", propertyResolver.getProperty(dsPrefix + ".password"));
            dsMap.put("filters", propertyResolver.getProperty(dsPrefix + ".filters"));
            dsMap.put("connection-properties", propertyResolver.getProperty(dsPrefix + ".connection-properties"));
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, env);

            log.info("Druid DataSource init > 次数据源{}", dsPrefix);
        }
    }

    /**
     * 为DataSource绑定更多数据
     * param dataSource
     * param env
     */
    private void dataBinder(DataSource dataSource, Environment env) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);
        if (dataSourcePropertyValues == null) {
            RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.druid");
            Map<String, Object> rpr = propertyResolver.getSubProperties(".");
            Map<String, Object> values = new HashMap<>(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            values.remove("connection-properties");
            values.remove("filters");
            values.put("connection-properties", propertyResolver.getProperty(".connection-properties"));
            values.put("filters", propertyResolver.getProperty(".filters"));
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }
}

