package com.dyenigma.twinsapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : 定时任务测试
 * @Author : dingdongliang
 * @Date : 2018/4/2 18:14
 */
@Configuration
@EnableScheduling
@Slf4j
public class TaskConfigure {

    @Scheduled(cron = "0 0/20 * * * ?")
    public void scheduler() {
        log.info(">>>>>>>>>>>>> 定时任务scheduled测试 ... ");
    }
}