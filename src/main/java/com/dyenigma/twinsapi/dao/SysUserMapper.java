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
     * 根据用户名和密码查询对应的用*
     * @param account  用户账号
     * @param password 密码
     * @return
     */
    SysUser userCertified(@Param("account") String account, @Param("password") String password);
}