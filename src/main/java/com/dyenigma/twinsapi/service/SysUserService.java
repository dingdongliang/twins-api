package com.dyenigma.twinsapi.service;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.entity.SysUser;

/**
 * twins-api/com.dyenigma.twinsapi.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 15:41
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 登录表单提交
     * @author dingdongliang
     * @date 2018/4/11 16:29
     */
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * @param account
     * @param password
     * @return com.dyenigma.twinsapi.entity.SysUser
     * @Description: 根据用户名和密码查询对应的用户, 用于登录认证
     * @author dingdongliang
     * @date 2018/4/11 16:52
     */
    SysUser userCertified(String account, String password);

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 退出登录
     * @author dingdongliang
     * @date 2018/4/11 16:29
     */
    JSONObject logout();
}
