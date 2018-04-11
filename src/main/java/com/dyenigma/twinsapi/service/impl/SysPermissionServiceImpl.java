package com.dyenigma.twinsapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.dao.SysPermissionMapper;
import com.dyenigma.twinsapi.entity.SysPermission;
import com.dyenigma.twinsapi.service.SysPermissionService;
import com.dyenigma.twinsapi.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * twins-api/com.dyenigma.twinsapi.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 16:38
 */
@Service
@Slf4j
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 查询当前登录用户的权限等信息
     * @author dingdongliang
     * @date 2018/4/10 9:42
     */
    @Override
    public JSONObject getPermissions(String userId) {
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
}
