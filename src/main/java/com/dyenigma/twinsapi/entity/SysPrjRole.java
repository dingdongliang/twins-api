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
public class SysPrjRole extends BaseDomain {
    private String prId;

    private String prjId;

    private String roleId;

    private String status;

}