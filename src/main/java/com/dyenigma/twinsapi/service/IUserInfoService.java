package com.dyenigma.twinsapi.service;

import com.alibaba.fastjson.JSONObject;

/**
 * twins/com.dyenigma.twinsapi.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 18:55
 */
public interface IUserInfoService {

    JSONObject deleteByPrimaryKey(JSONObject jsonObject);

    JSONObject insert(JSONObject jsonObject);

    JSONObject selectByPrimaryKey(JSONObject jsonObject);

    JSONObject updateByPrimaryKey(JSONObject jsonObject);

    JSONObject selectAllUserInfo();

}
