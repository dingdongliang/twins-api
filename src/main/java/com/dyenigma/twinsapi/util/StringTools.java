package com.dyenigma.twinsapi.util;

/**
 * twins/com.dyenigma.twinsapi.util
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:21
 */
public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
