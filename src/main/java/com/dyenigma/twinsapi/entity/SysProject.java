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
public class SysProject extends BaseDomain {
    private String prjId;

    private String prjName;

    private String prjDesc;

    private String leader;

    private String status;

    private String coId;


}