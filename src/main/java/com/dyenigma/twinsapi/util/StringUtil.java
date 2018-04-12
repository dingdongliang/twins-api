package com.dyenigma.twinsapi.util;

import com.dyenigma.twinsapi.core.SystemConstant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

/**
 * twins/com.dyenigma.twinsapi.util
 *
 * @Description : 字符串工具类，包含生成32位UUID方法
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:21
 */
public class StringUtil {
    private StringUtil() {
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

    public static void main(String[] args) {
        System.out.println(encryptPassword("admin","dyenigma"));
    }

    /**
     * @param
     * @return java.lang.String
     * @Description: 生成一个主键
     * @author dingdongliang
     * @date 2018/3/28 17:04
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * @param number 生成主键的个数
     * @return java.lang.String[]
     * @Description: 生成number个主键
     * @author dingdongliang
     * @date 2018/3/28 17:04
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
