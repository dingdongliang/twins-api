package com.dyenigma.twinsapi.service;

import com.dyenigma.twinsapi.TwinsApiApplicationTests;
import com.dyenigma.twinsapi.core.SystemConstant;
import com.dyenigma.twinsapi.mongo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * twins-api/com.dyenigma.twinsapi.service
 *
 * @Description : SpringBoot集成MongoDB示例，业务类测试-用户信息操作（CURD+分页）
 * @Author : dingdongliang
 * @Date : 2018/4/9 17:27
 */
@Slf4j
public class UserInfoServiceTest extends TwinsApiApplicationTests {

    @Autowired
    private UserInfoService userInfoService;


    @Test
    public void saveUserTest() {
        String userName = "dyenigma";
        String userAddr = "河南省郑州市";
        UserInfo userInfo = new UserInfo(userName, userAddr);
        userInfoService.save(userInfo);
        log.info("存储用户信息测试成功");
    }

    @Test
    public void findAllUserByPageTest() {
        Map userMap = userInfoService.findByPage(1);
        log.info("分页查询结果-总页数:{}", userMap.get(SystemConstant.TOTAL));
        List<UserInfo> userInfoList = (List<UserInfo>) userMap.get(SystemConstant.RESULT);
        for (UserInfo userInfo : userInfoList) {
            log.info(userInfo.toString());
        }
        log.info("分页测试成功");
    }

}
