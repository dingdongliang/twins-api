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
     * 登录表单提交
     *
     * @param account
     * @param password
     * @return com.alibaba.fastjson.JSONObject
     * @author dingdongliang
     * @date 2018/4/12 17:49
     */
    JSONObject authLogin(String account, String password);

    /**
     * 根据用户名和密码查询对应的用户, 用于登录认证
     *
     * @param account
     * @param password
     * @return com.dyenigma.twinsapi.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:45
     */
    SysUser userCertified(String account, String password);

    /**
     * 退出登录
     *
     * @return com.alibaba.fastjson.JSONObject
     * @author dingdongliang
     * @date 2018/4/12 17:45
     */
    JSONObject logout();
}
