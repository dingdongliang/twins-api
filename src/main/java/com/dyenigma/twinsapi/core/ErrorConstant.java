package com.dyenigma.twinsapi.core;

/**
 * twins-api/com.dyenigma.twinsapi.core
 *
 * @Description : 错误回复常量
 * @Author : dingdongliang
 * @Date : 2018/4/11 17:48
 */
public class ErrorConstant {

    private ErrorConstant() {
    }

    public static final String NO_ACCOUNT = "未知账户";

    public static final String PWD_ERROR = "密码不正确";
    public static final String ACCOUNT_LOCKED = "账户已锁定";
    public static final String TRY_MORE_FIVE = "错误次数大于5次,账户已锁定";
    public static final String ACCOUNT_FORBID = "帐号已经禁止登录";

    public static final String STH_ERROR = "用户名或密码不正确";

}
