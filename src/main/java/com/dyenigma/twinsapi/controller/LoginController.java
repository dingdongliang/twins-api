package com.dyenigma.twinsapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.service.SysUserService;
import com.dyenigma.twinsapi.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * twins-api/com.dyenigma.twinsapi.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/10 7:50
 */
@RestController
@Api(value = "P2P图书馆测试-登录控制器接口")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * @param requestJson
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 登录验证
     * @author dingdongliang
     * @date 2018/4/11 17:57
     */
    @ApiOperation(value = "用户登陆方法", notes = "详细说明文档")
    @PostMapping("/login")
    public JSONObject authLogin(@ApiParam(name = "requestJson",
            value = "格式为{\"username\": \"admin\"," + "\"password\": \"admin\"}", required = true)
                                @RequestBody JSONObject requestJson) {
        JsonUtil.hasAllRequired(requestJson, "username,password");
        return sysUserService.authLogin(requestJson);
    }


    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description: 登出
     * @author dingdongliang
     * @date 2018/4/11 18:01
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return sysUserService.logout();
    }
}

