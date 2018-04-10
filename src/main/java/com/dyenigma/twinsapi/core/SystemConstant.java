package com.dyenigma.twinsapi.core;

/**
 * twins/com.dyenigma.twinsapi.param
 *
 * @Description : SpringBoot集成MongoDB示例，系统常量类- 配置通用的一些参数常量
 * @Author : dingdongliang
 * @Date : 2018/3/28 9:35
 */
public class SystemConstant {
    private SystemConstant() {
    }

    /**
     * 一些常用的字符串常量，常量值和名称保持一致,值都是小写，用来避免魔法值
     */
    public static final String OK = "ok";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String FALSE = "false";
    public static final String UNKNOWN = "unknown";
    public static final String COMMA = ",";
    public static final String SUCCESS_CODE = "1";
    public static final String SUCCESS_MSG = "请求成功";

    public static final String ALGORITHMNAME = "md5";
    public static final Integer HASHITERATIONS = 2;


    /**
     * session中存放用户信息的key值
     */
    public static final String SESSION_USER_INFO = "userInfo";
    public static final String SESSION_USER_PERMISSION = "userPermission";

    /**
     * 分页相关数据
     */
    public static final String RESULT = "data";
    public static final String TOTAL = "totalPages";
    public static final Integer PAGE_SIZE = 10;

    /**
     * ENFLAG表示已加密，或者已发送，使用字符串常量"did"
     */
    public static final String ENFLAG = "did";

    /**
     * NOFLAG表示未加密，或者未发送，使用字符串常量"undo"
     */
    public static final String NOFLAG = "undo";


}
