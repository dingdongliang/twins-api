package com.dyenigma.twinsapi.dao;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * twins/com.dyenigma.twinsapi.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:47
 */
public interface SysPermissionMapper extends MyMapper<JSONObject> {
    /**
     * 查询用户的角色 菜单 权限
     *
     * @param userId
     * @return java.util.List<com.dyenigma.twinsapi.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/12 17:54
     */
    List<SysPermission> getUserPermission(@Param("userId") String userId);

}