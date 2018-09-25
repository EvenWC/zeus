package com.wangcheng.zeus.common.web.exception.strategy.annotation;
import com.wangcheng.zeus.common.web.exception.strategy.GlobalExceptionHandlerBeanRegister;
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
@Import(GlobalExceptionHandlerBeanRegister.class)
public @interface EnableExceptionHandle {

}
