package com.dyenigma.twinsapi.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * twins/com.dyenigma.twinsapi.druid
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 15:07
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "allow", value = "127.0.0.1"), //IP白名单
                @WebInitParam(name = "deny", value = "128.242.127.4"),// IP黑名单 (优先级deny>allow)
                @WebInitParam(name = "loginUsername", value = "admin"),// druid登录时的用户名
                @WebInitParam(name = "loginPassword", value = "admin"),// druid登录时的密码
                @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
        })
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = 1L;

}
