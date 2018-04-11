package com.dyenigma.twinsapi.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * twins/com.dyenigma.twinsapi.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:47
 */
public interface SysPermissionMapper extends MyMapper<JSONObject> {
    /**
     * @param username
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 查询用户的角色 菜单 权限
     * @author dingdongliang
     * @date 2018/4/9 17:21
     */
    JSONObject getUserPermission(String username);

}