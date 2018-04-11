package com.dyenigma.twinsapi.util;

import com.dyenigma.twinsapi.core.SystemConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * twins-api/com.dyenigma.twinsapi.util
 *
 * @Description : 从代理中获取请求的真实IP地址
 * @Author : dingdongliang
 * @Date : 2018/4/11 17:18
 */
public class IpUtil {

    private IpUtil() {
    }

    public static String getIpAddr(HttpServletRequest request) {

        if (request == null) {
            return SystemConstant.UNKNOWN;
        }

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || SystemConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || SystemConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || SystemConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || SystemConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || SystemConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}

