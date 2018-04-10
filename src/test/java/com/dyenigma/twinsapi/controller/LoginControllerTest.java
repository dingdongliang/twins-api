package com.dyenigma.twinsapi.controller;

import com.dyenigma.twinsapi.TwinsApiApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * twins-api/com.dyenigma.twinsapi.controller
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/10 7:58
 */
@Slf4j
public class LoginControllerTest extends TwinsApiApplicationTests {
    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter((Filter) wac.getBean("shiroFilter")).build();
    }


    @Test
    public void authLoginTest() throws Exception {
        String loginInfo = "{\"username\":\"admin\",\"password\":\"admin\"}";

        String responseString = mockMvc.perform(post("/login/auth")
                .contentType(MediaType.APPLICATION_JSON).content(loginInfo))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("--------返回的json = " + responseString);
    }
}
