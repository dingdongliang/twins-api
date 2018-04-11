package com.dyenigma.twinsapi.service;

import com.dyenigma.twinsapi.mongo.UserInfo;

/**
 * twins/com.dyenigma.twinsapi.service
 *
 * @Description : SpringBoot集成MongoDB示例，业务类接口-用户信息操作（CURD+分页）
 * @Author : dingdongliang
 * @Date : 2018/3/11 18:55
 */
public interface UserInfoService extends MongoBaseService<UserInfo> {
}
