package com.dyenigma.twinsapi.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * twins-api/com.dyenigma.twinsapi.shiro
 *
 * @Description : 自定义过滤器，未授权则弹出403错误
 * @Author : dingdongliang
 * @Date : 2018/4/11 15:28
 */
public class AuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            WebUtils.toHttp(response).sendError(403);
        }
        return false;
    }
}

