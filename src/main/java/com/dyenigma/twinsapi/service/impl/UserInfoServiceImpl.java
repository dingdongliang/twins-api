package com.dyenigma.twinsapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.dao.UserInfoMapper;
import com.dyenigma.twinsapi.service.IUserInfoService;
import com.dyenigma.twinsapi.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * twins/com.dyenigma.twinsapi.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 18:57
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public JSONObject deleteByPrimaryKey(JSONObject jsonObject) {
        userInfoMapper.deleteByPrimaryKey(jsonObject);
        return JsonUtil.successJson();
    }

    @Override
    public JSONObject insert(JSONObject jsonObject) {
        userInfoMapper.insert(jsonObject);
        return JsonUtil.successJson();
    }

    @Override
    public JSONObject selectByPrimaryKey(JSONObject jsonObject) {
        return userInfoMapper.selectByPrimaryKey(jsonObject);
    }

    @Override
    public JSONObject updateByPrimaryKey(JSONObject jsonObject) {
        userInfoMapper.updateByPrimaryKey(jsonObject);
        return JsonUtil.successJson();
    }

    @Override
    public JSONObject selectAllUserInfo() {
        List<JSONObject> userInfos = userInfoMapper.selectAllUserInfo();
        return JsonUtil.successJson(userInfos);
    }
}
