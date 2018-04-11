package com.dyenigma.twinsapi.service;

import com.alibaba.fastjson.JSONObject;

/**
 * twins-api/com.dyenigma.twinsapi.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 15:42
 */
public interface SysPermissionService {

    JSONObject getPermissions(String userId);

}
