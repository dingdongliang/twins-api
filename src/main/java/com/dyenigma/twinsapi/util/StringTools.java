package com.dyenigma.twinsapi.util;

import com.dyenigma.twinsapi.core.SystemConstant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * twins/com.dyenigma.twinsapi.util
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:21
 */
public class StringTools {
    private StringTools() {
    }

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }

    /**
     * @param pwd
     * @param name
     * @return java.lang.String
     * @Description: 根据用户名和密码进行两次MD5加密
     * @author dingdongliang
     * @date 2018/4/10 14:04
     */
    public static String encryptPassword(String pwd, String name) {
        return new SimpleHash(SystemConstant.ALGORITHMNAME, pwd, ByteSource.Util.bytes(name),
                SystemConstant.HASHITERATIONS).toHex();
    }
}
