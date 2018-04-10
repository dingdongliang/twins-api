package com.dyenigma.twinsapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.service.LoginService;
import com.dyenigma.twinsapi.util.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * twins-api/com.dyenigma.twinsapi.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/10 7:50
 */
@RestController
@Api(description = "P2P图书馆测试-登录控制器接口")
@RequestMapping(value ="/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "username,password");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     *
     * @return
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return loginService.logout();
    }
}

