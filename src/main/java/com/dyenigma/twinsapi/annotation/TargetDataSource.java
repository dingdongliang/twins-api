package com.dyenigma.twinsapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * twins/com.dyenigma.twinsapi.annotation
 *
 * @Description :用于指定使用哪个druid数据源，在方法上使用@TargetDataSource("master")
 * @Author : dingdongliang
 * @Date : 2018/4/2 10:38
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
