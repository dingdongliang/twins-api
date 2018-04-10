package com.dyenigma.twinsapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.dao.SysPermissionMapper;
import com.dyenigma.twinsapi.dao.SysUserMapper;
import com.dyenigma.twinsapi.service.LoginService;
import com.dyenigma.twinsapi.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
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
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 登录表单提交
     * @author dingdongliang
     * @date 2018/4/10 9:42
     */
    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject returnData = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            returnData.put("result", "success");
        } catch (AuthenticationException e) {
            returnData.put("result", "fail");
        }
        return JsonUtil.successJson(returnData);
    }

    /**
     * @param username
     * @param password
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 根据用户名和密码查询对应的用户
     * @author dingdongliang
     * @date 2018/4/10 9:42
     */
    @Override
    public JSONObject getUser(String username, String password) {
        return sysUserMapper.getUser(username, password);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 查询当前登录用户的权限等信息
     * @author dingdongliang
     * @date 2018/4/10 9:42
     */
    @Override
    public JSONObject getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(SystemConstant.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject returnData = new JSONObject();
        JSONObject userPermission = sysPermissionMapper.getUserPermission(username);
        session.setAttribute(SystemConstant.SESSION_USER_PERMISSION, userPermission);
        returnData.put("userPermission", userPermission);
        return JsonUtil.successJson(returnData);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 退出登录
     * @author dingdongliang
     * @date 2018/4/10 9:42
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

