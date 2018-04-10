package com.dyenigma.twinsapi.shiro;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * twins/com.dyenigma.twinsapi.shiro
 *
 * @Description : 自定义realm
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:30
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    private static final String ALGORITHM = "MD5";

    public UserRealm() {
        super();
    }

    /**
     * @param principals
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @Description: 获取授权信息
     * @author dingdongliang
     * @date 2018/4/10 9:38
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        JSONObject permission = (JSONObject) session.getAttribute(SystemConstant.SESSION_USER_PERMISSION);
        log.info("permission的值为:" + permission);
        log.info("本用户权限为:" + permission.get("permissionList"));
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }


    /**
     * @param authcToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @Description: 获取认证信息, 验证当前登录的Subject, 登录控制器login()方法执行Subject.login()时调用此方法
     * @author dingdongliang
     * @date 2018/4/10 9:38
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws
            AuthenticationException {
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authcToken.getCredentials());
        JSONObject user = loginService.getUser(loginName, password);
        if (user == null) {
            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getString("USER_NAME"),
                user.getString("PASSWORD"),
                getName()
        );
        //session中不需要保存密码
        user.remove("password");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(SystemConstant.SESSION_USER_INFO, user);
        return authenticationInfo;
    }


    /**
     * @param principal
     * @return void
     * @Description: 更新用户授权信息缓存.
     * @author dingdongliang
     * @date 2018/4/10 11:44
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * @return void
     * @Description: 清除所有用户授权信息缓存.
     * @author dingdongliang
     * @date 2018/4/10 11:44
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    @PostConstruct
    public void initCredentialsMatcher() {// MD5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
        matcher.setStoredCredentialsHexEncoded(true);
        setCredentialsMatcher(matcher);
    }
}

