package com.wangcheng.zeus.common.web.exception.register;

import com.wangcheng.zeus.common.web.exception.register.EnableExceptionHandlerRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @Date: 2018/9/24 09:51
 * @Description: 实现可以自定义异常处理的能力
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableExceptionHandlerRegister.class)
public @interface EnableExceptionHandler {
    /**
     * 禁用
     * @return
     */
    boolean disabled() default false;
}
