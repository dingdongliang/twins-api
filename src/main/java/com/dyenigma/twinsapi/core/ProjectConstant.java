package com.dyenigma.twinsapi.core;

/**
 * twins/com.dyenigma.twinsapi.param
 *
 * @Description : 项目常量
 * @Author : dingdongliang
 * @Date : 2018/3/30 16:47
 */
public class ProjectConstant {

    /**
     * 项目基础包名称，根据自己的项目修改
     */
    public static final String BASE_PACKAGE = "com.dyenigma.twinsapi";
    /**
     * Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";

    /**
     * Mapper所在包
     */

    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";
    /**
     * Service所在包
     */

    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    /**
     * ServiceImpl所在包
     */

    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    /**
     * Controller所在包
     */
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

}
