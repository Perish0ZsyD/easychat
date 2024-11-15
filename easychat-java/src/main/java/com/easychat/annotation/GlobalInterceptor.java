package com.easychat.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;
/**
 * @ClassName GlobalInterceptor
 * @Description
 * @Author Siyuan
 * @Date 2024/11/15/20:56
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE}) // 作用在哪：作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时调用
@Documented // 生成文档
@Mapping // 作为一个映射
public @interface GlobalInterceptor {

    /**
     * 校验登录，登陆后的接口都需要进行登录校验（接口拦截），登陆之前的接口不需要
     *
     * @return
     */
    boolean checkLogin() default true;

    /**
     * 校验管理员
     *
     * @return
     */
    boolean checkAdmin() default false;
}
