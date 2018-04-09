package com.dyenigma.twinsapi.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * twins/com.dyenigma.twinsapi.entity
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class SysUserPmsn extends BaseDomain {
    private String upmId;

    private String userId;

    private String pmsnId;

    private String status;
}