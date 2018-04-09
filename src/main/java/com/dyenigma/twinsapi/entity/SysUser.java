package com.dyenigma.twinsapi.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * twins/com.dyenigma.twinsapi.entity
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class SysUser extends BaseDomain {
    private String userId;

    private String userName;

    private String userNo;

    private Date joinTime;

    private String account;

    private String password;

    private String userEmail;

    private String userPhone;

    private String userAddr;

    private String gender;

    private Date firstLogin;

    private Date prevLogin;

    private String prevIp;

    private Date lastLogin;

    private String loginCount;

    private String status;

    private String userDesc;

    private Integer isOnline;

    private Integer sort;
}