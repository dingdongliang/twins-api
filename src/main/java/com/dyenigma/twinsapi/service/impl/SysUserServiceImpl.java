package com.dyenigma.twinsapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.ErrorConstant;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.dao.SysUserMapper;
import com.dyenigma.twinsapi.entity.SysUser;
import com.dyenigma.twinsapi.service.SysUserService;
import com.dyenigma.twinsapi.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * twins/com.dyenigma.twinsapi.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/3 9:43
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    private static final String RESULT = "result";

    /**
     * 登录表单提交
     *
     * @return com.alibaba.fastjson.JSONObject
     * @author dingdongliang
     * @date 2018/4/12 17:47
     */
    @Override
    public JSONObject authLogin(String account,String password) {
        JSONObject returnData = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            //调用ShiroRealm的doGetAuthenticationInfo方法
            currentUser.login(token);
            returnData.put(RESULT, SystemConstant.SUCCESS);
        } catch (UnknownAccountException e) {
            log.error(ErrorConstant.NO_ACCOUNT);
            returnData.put(RESULT, ErrorConstant.NO_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
            log.error(ErrorConstant.PWD_ERROR);
            returnData.put(RESULT, ErrorConstant.PWD_ERROR);
        } catch (LockedAccountException e) {
            log.error(ErrorConstant.ACCOUNT_LOCKED);
            returnData.put(RESULT, ErrorConstant.ACCOUNT_LOCKED);
        } catch (ExcessiveAttemptsException e) {
            log.error(ErrorConstant.TRY_MORE_FIVE);
            returnData.put(RESULT, ErrorConstant.TRY_MORE_FIVE);
        } catch (DisabledAccountException e) {
            log.error(ErrorConstant.ACCOUNT_FORBID);
            returnData.put(RESULT, ErrorConstant.ACCOUNT_FORBID);
        } catch (AuthenticationException e) {
            log.error(ErrorConstant.STH_ERROR);
            returnData.put(RESULT, ErrorConstant.STH_ERROR);
        }
        return JsonUtil.successJson(returnData);
    }

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param account
     * @param password
     * @return com.dyenigma.twinsapi.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:46
     */
    @Override
    public SysUser userCertified(String account, String password) {
        return sysUserMapper.userCertified(account, password);
    }


    /**
     * 退出登录
     *
     * @return com.alibaba.fastjson.JSONObject
     * @author dingdongliang
     * @date 2018/4/12 17:46
     */
    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return JsonUtil.successJson();
    }
}

