package com.dyenigma.twinsapi.service;

import com.dyenigma.twinsapi.entity.SysPermission;

import java.util.List;

/**
 * twins-api/com.dyenigma.twinsapi.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 15:42
 */
public interface SysPermissionService {

    /**
     * 查询用户的角色 菜单 权限
     *
     * @param userId
     * @return java.util.List<com.dyenigma.twinsapi.entity.SysPermission>
     * @author dingdongliang
     * @date 2018/4/12 17:54
     */
    List<SysPermission> getPermissions(String userId);

}
