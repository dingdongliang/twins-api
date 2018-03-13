package com.dyenigma.twinsapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.service.IUserInfoService;
import com.dyenigma.twinsapi.util.JsonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * twins/com.dyenigma.twinsapi.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:04
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @Resource
    IUserInfoService iUserInfoService;

    @GetMapping(value = "/getAllUser")
    public JSONObject getAllUser() {
        return iUserInfoService.selectAllUserInfo();
    }

    @PostMapping(value = "/deleteUser")
    public JSONObject deleteUser(@RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "userId");
        return iUserInfoService.deleteByPrimaryKey(requestJson);
    }

    @PostMapping(value = "/addUser")
    public JSONObject addUser(@RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "userName");
        return iUserInfoService.insert(requestJson);
    }

    @GetMapping(value = "/selectUserById")
    public JSONObject selectUserById(@RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "userId");
        return iUserInfoService.selectByPrimaryKey(requestJson);
    }

    @GetMapping(value = "/updateUser")
    public JSONObject updateUser(@RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "userId");
        return iUserInfoService.updateByPrimaryKey(requestJson);
    }
}