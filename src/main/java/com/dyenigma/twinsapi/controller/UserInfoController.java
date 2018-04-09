package com.dyenigma.twinsapi.controller;

import com.alibaba.fastjson.JSON;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.mongo.UserInfo;
import com.dyenigma.twinsapi.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * twins/com.dyenigma.twinsapi.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:04
 */
@RestController
@Api(value = "SpringBoot集成MongoDB示例控制类API，P2P图书馆测试用户信息系列接口")
@RequestMapping(value = "/userInfo")
@Slf4j
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "存储用户信息接口", notes = "输入：用户名称和用户地址，调用此接口保存到数据库")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userAddr", value = "用户地址", required = true, dataType = "String")
    })
    @PostMapping(value = "/v1/saveUser", produces = "application/json;charset=utf-8")
    public String saveUser(@RequestParam String userName, @RequestParam String userAddr) {

        log.info("需要保存的用户信息-用户名：{},地址：{}", userName, userAddr);

        UserInfo userInfo = new UserInfo(userName, userAddr);

        userInfoService.save(userInfo);

        return JSON.toJSONString(SystemConstant.SUCCESS);
    }

    @RequestMapping(value = "/test/sessions/insert", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("request Url", request.getRequestURL());
        request.getSession().setAttribute("map", map);
        return map;
    }

    @RequestMapping(value = "/test/sessions/find", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }
}
