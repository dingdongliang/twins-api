package com.dyenigma.twinsapi.config;


import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.shiro.AuthenticationFilter;
import com.dyenigma.twinsapi.shiro.KickoutSessionControlFilter;
import com.dyenigma.twinsapi.shiro.ShiroRealm;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:09
 */
@Configuration
@Slf4j
public class ShiroConfigure {


    @Resource
    private Environment env;

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehcacheManager;
    }


    /**
     * @return org.apache.shiro.web.servlet.SimpleCookie
     * @Description: 设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等
     * @author dingdongliang
     * @date 2018/4/11 14:43
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间30天 ,单位秒
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     * @Description: 生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @author dingdongliang
     * @date 2018/4/11 14:45
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //加密的密钥,默认AES算法,密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode(SystemConstant.AES_KEY));
        return cookieRememberMeManager;
    }

    /**
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Description: Shiro的Web过滤器Factory 命名:shiroFilter
     * @author dingdongliang
     * @date 2018/4/11 15:39
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //自定义拦截器，包含限制同一帐号同时在线的个数，添加casFilter到shiroFilter中，未授权的跳转等
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put(SystemConstant.KICKOUT, kickoutSessionControlFilter());
        filtersMap.put("authc", new AuthenticationFilter());

        shiroFilter.setFilters(filtersMap);

        Map<String, String> filterChainMap = new LinkedHashMap<>();

        filterChainMap.put("/", "anon");
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/error", "anon");

        //有关swagger2的配置，生产环境中注意修改
        filterChainMap.put("/v2/**", SystemConstant.VISIT_SETTING);
        filterChainMap.put("/swagger/**", SystemConstant.VISIT_SETTING);
        filterChainMap.put("/swagger-ui.html", SystemConstant.VISIT_SETTING);
        filterChainMap.put("/swagger-resources/**", SystemConstant.VISIT_SETTING);
        filterChainMap.put("/webjars/**", SystemConstant.VISIT_SETTING);

        //数据源druid访问的控制，生产环境中注意修改
        filterChainMap.put("/druid/**", SystemConstant.VISIT_SETTING);

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainMap.put("/logout", "logout");

        filterChainMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainMap);

        return shiroFilter;
    }


    /**
     * @return org.apache.shiro.mgt.SecurityManager
     * @Description: 不指定名字的话，自动创建一个方法名第一个字母小写的bean
     * @author dingdongliang
     * @date 2018/4/10 8:48
     */
    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        //用户授权/认证信息Cache, 采用EhCache 缓存
        securityManager.setCacheManager(getEhCacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setSubjectFactory(new Pac4jSubjectFactory());
        return securityManager;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();

        kickoutSessionControlFilter.setCacheManager(getEhCacheManager());
        kickoutSessionControlFilter.setKickoutAfter(SystemConstant.KICKOUT_AFTER);
        kickoutSessionControlFilter.setMaxSession(SystemConstant.ACCOUNT_MAX_SESSION);

        return kickoutSessionControlFilter;
    }


    /**
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     * @Description: 凭证匹配器，可以扩展实现输入密码错误次数后锁定等功能
     * @author dingdongliang
     * @date 2018/4/10 8:49
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(SystemConstant.ALGORITHMNAME);
        hashedCredentialsMatcher.setHashIterations(SystemConstant.HASHITERATIONS);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * @return org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @Description: Shiro生命周期处理器
     * @author dingdongliang
     * @date 2018/4/10 8:50
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @Description: 开启shiro aop注解支持.
     * @author dingdongliang
     * @date 2018/4/10 9:26
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
