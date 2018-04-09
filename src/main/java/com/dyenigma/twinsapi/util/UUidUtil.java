package com.dyenigma.twinsapi.util;

import java.util.UUID;

/**
 * twins/com.dyenigma.twinsapi.util
 *
 * @Description : 生成数据库主键的工具类
 * @Author : dingdongliang
 * @Date : 2018/3/28 17:03
 */
public class UUidUtil {
    private UUidUtil() {
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
