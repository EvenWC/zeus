package com.wangcheng.zeus.demo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Auther: Administrator
 * @Date: 2018/9/15 19:46
 * @Description:使用切片进行拦截
 */
@Aspect
//@Component
public class ControlAspect {

    @Around("execution(* com.wangcheng.zeus.demo.web.control.*.*(*) )")
    public Object handle(ProceedingJoinPoint pjp)  {
        System.out.println("aspect start ......");
        long start = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        for (Object arg: args) {
            System.out.println("arg --->" +arg);
        }
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("切片异常");
        }
        System.out.println(proceed);
        System.out.println("aspect time :"+(System.currentTimeMillis() - start));
        System.out.println("aspect end ....");

        return proceed;
    }
}
