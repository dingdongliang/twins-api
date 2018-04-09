package com.dyenigma.twinsapi.controller;

import com.dyenigma.twinsapi.TwinsApiApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * twins-api/com.dyenigma.twinsapi.controller
 *
 * @Description : SpringBoot集成MongoDB示例，控制类测试-前端用户信息交互控制
 * @Author : dingdongliang
 * @Date : 2018/4/9 17:25
 */
@Slf4j
public class UserInfoControllerTest extends TwinsApiApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void saveUserTest() throws Exception {
        String userName = "dyenigma";
        String userAddr = "河南省郑州市金水区";

        MvcResult result = mockMvc.perform(post("/userInfo/v1/saveUser")
                .param("userName", userName)
                .param("userAddr", userAddr))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("测试返回值为：" + result.getResponse().getContentAsString());
    }
}
