package com.wangcheng.zeus.common.web.exception.strategy.annotation;


import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * @Auther: Administrator
 * @Date: 2018/8/30 21:40
 * @Description: 异常处理策略的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExceptionHandleType {
    Class<? extends Exception>[] value();
}
