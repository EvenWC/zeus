package com.wangcheng.zeus.common.web.exception.strategy.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: Administrator
 * @Date: 2018/8/30 21:40
 * @Description: 异常处理策略的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandle {
    Class<? extends Exception>[] value();
}
