package com.dyenigma.twinsapi.dao;

import com.dyenigma.twinsapi.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * twins/com.dyenigma.twinsapi.dao
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:47
 */
public interface SysUserMapper extends MyMapper<SysUser> {


    /**
     * 根据用户名和密码查询对应的用户，用于登录校验
     *
     * @param account
     * @param password
     * @return com.dyenigma.twinsapi.entity.SysUser
     * @author dingdongliang
     * @date 2018/4/12 17:43
     */
    SysUser userCertified(@Param("account") String account, @Param("password") String password);
}