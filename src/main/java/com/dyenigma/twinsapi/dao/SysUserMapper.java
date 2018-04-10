package com.dyenigma.twinsapi.dao;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.OwnMapper;
import org.apache.ibatis.annotations.Param;

/**
 * twins/com.dyenigma.twinsapi.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:47
 */
public interface SysUserMapper extends OwnMapper<JSONObject> {
    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    JSONObject getUser(@Param("username") String username, @Param("password") String password);
}