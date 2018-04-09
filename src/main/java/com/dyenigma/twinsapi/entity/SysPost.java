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
public class SysPost extends BaseDomain {
    private String postId;

    private String postName;

    private String divId;

    private String postDesc;

    private String status;

}