package com.dyenigma.twinsapi.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    List<JSONObject> selectAllUserInfo();

    JSONObject selectByPrimaryKey(JSONObject jsonObject);

    int updateByPrimaryKey(JSONObject jsonObject);
}