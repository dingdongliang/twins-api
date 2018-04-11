package com.dyenigma.twinsapi.shiro;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.core.RespCodeEnum;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 * @Author : dingdongliang
 * @Date : 2018/4/3 8:11
 */
public class AjaxPermissionsFilter extends FormAuthenticationFilter {

    /**
     * @param request
     * @param response
     * @return boolean
     * @Description: 当访问拒绝时是否已经处理, 返回true表示需要继续处理；返回false将直接返回
     * @author dingdongliang
     * @date 2018/4/11 12:49
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", RespCodeEnum.LOGOUT_PLEASE_RE_LOGIN.getCode());
        jsonObject.put("returnMsg", RespCodeEnum.LOGOUT_PLEASE_RE_LOGIN.getDesc());
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return super.onAccessDenied(request, response);
    }

    @Bean
    public FilterRegistrationBean registration(AjaxPermissionsFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}

