package com.dyenigma.twinsapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.dao.SysPermissionMapper;
import com.dyenigma.twinsapi.entity.SysPermission;
import com.dyenigma.twinsapi.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * 查询当前登录用户的权限等信息
     *
     * @param userId
     * @return com.alibaba.fastjson.JSONObject
     * @author dingdongliang
     * @date 2018/4/12 17:46
     */
    @Override
    public List<SysPermission> getPermissions(String userId) {

        List<SysPermission> sysPermissionList = sysPermissionMapper.getUserPermission(userId);

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(SystemConstant.SESSION_USER_PERMISSION, sysPermissionList);

        JSONObject returnData = new JSONObject();
        returnData.put("userPermission", sysPermissionList);
        return sysPermissionList;

    }
}
