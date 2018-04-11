package com.dyenigma.twinsapi.util;

import java.io.UnsupportedEncodingException;

/**
 * twins-api/com.dyenigma.twinsapi.util
 *
 * @Description : URL处理工具
 * @Author : dingdongliang
 * @Date : 2018/4/11 17:28
 */
public class URLUtils extends org.springframework.web.util.UriUtils {

    private URLUtils() {
    }

    public static String encodeURL(String source, String encoding) {
        try {
            return URLUtils.encode(source, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
