package com.dyenigma.twinsapi.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * twins/com.dyenigma.twinsapi.druid
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 15:06
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
                @WebInitParam(name = "config.decrypt", value = "true")
        })
public class DruidStatFilter extends WebStatFilter {

}
