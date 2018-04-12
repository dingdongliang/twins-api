package com.dyenigma.twinsapi.shiro;

import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.entity.SysPermission;
import com.dyenigma.twinsapi.entity.SysUser;
import com.dyenigma.twinsapi.service.SysPermissionService;
import com.dyenigma.twinsapi.service.SysUserService;
import com.dyenigma.twinsapi.util.StringUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
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
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * twins/com.dyenigma.twinsapi.shiro
 *
 * @Description : 自定义realm
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:30
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysPermissionService sysPermissionService;

    private static final String ALGORITHM = "MD5";
    private static final String PERMISSION = "permissionList";

    public ShiroRealm() {
        super();
    }

    /**
     * @param principals
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @Description: 获取授权信息，首先检查Session中的信息，如果为空再重新获取
     * @author dingdongliang
     * @date 2018/4/10 9:38
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Session session = SecurityUtils.getSubject().getSession();
        @NonNull List<SysPermission> permissionList = (List<SysPermission>) session.getAttribute(SystemConstant
                .SESSION_USER_PERMISSION);

        log.info("permission的值为:" + permissionList);

        Set<String> permissionSet;

        if (permissionList == null) {
            SysUser user = (SysUser) principals.getPrimaryPrincipal();
            String userId = user.getUserId();

            //重新获取用户权限列表
            permissionList = sysPermissionService.getPermissions(userId);
        }

        permissionSet = new HashSet(permissionList);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissionSet);
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
        String username = (String) authcToken.getPrincipal();
        String password = new String((char[]) authcToken.getCredentials());
        password = StringUtil.encryptPassword(password, username);
        SysUser sysUser = sysUserService.userCertified(username, password);

        if (sysUser == null) {
            throw new UnknownAccountException("用户名或者密码不正确");
        }

        //账号禁用
        if (SystemConstant.INVALID.equals(sysUser.getStatus())) {
            throw new LockedAccountException("用户已被禁用,请联系管理员");
        }

        List<SysPermission> sysPermissionList = sysPermissionService.getPermissions(sysUser.getUserId());

        if (sysPermissionList != null) {
            sysUser.setPermissions(new HashSet(sysPermissionList));
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser.getAccount(), sysUser.getPassword(), getName());
        //session中不需要保存密码
        sysUser.setPassword("");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(SystemConstant.SESSION_USER_INFO, sysUser);
        return authenticationInfo;
    }


    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        SysUser shiroUser = (SysUser) principals.getPrimaryPrincipal();
        clearCachedAuthorizationInfo(shiroUser);
    }

    /**
     * @param user
     * @return void
     * @Description: 更新用户授权信息缓存.
     * @author dingdongliang
     * @date 2018/4/11 17:09
     */
    public void clearCachedAuthorizationInfo(SysUser user) {
        clearCachedAuthorizationInfo(user.getUserName());
    }

    /**
     * @param loginName
     * @return void
     * @Description: 更新用户授权信息缓存.
     * @author dingdongliang
     * @date 2018/4/11 17:09
     */
    public void clearCachedAuthorizationInfo(String loginName) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(loginName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
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

