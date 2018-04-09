package com.dyenigma.twinsapi.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : 前后端对接API文档自动生成配置类，在生产环境需要设置enable : false
 * @Author : dingdongliang
 * @Date : 2018/3/29 8:13
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger", value = {"enable"}, havingValue = "true")
@EnableSwagger2
public class SwaggerConfigure {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dyenigma")
                .apiInfo(new ApiInfoBuilder().title("P2P图书馆 API").version("1.0.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dyenigma.twinsapi.controller"))
                .build()
                .globalOperationParameters(null);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("dyenigma", "http://github.com/dingdongliang", "dyenigma@163.com");
        return new ApiInfoBuilder()
                .title("P2P图书馆 API")
                .description("build by dyenigma")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}