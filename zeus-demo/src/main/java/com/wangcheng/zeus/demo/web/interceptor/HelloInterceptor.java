package com.wangcheng.zeus.demo.web.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Administrator
 * @Date: 2018/9/15 12:50
 * @Description:
 */
@Component
public class HelloInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handle) throws Exception {
        System.out.println(httpServletRequest.getInputStream());
        HandlerMethod handle1Method =  (HandlerMethod)handle;
        System.out.println("preHandle ---->control name:"+handle1Method.getClass().getName());
        System.out.println("preHandle ---->method name:"+handle1Method.getMethod().getName());
        httpServletRequest.setAttribute("start",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long start = (Long)httpServletRequest.getAttribute("start");
        System.out.println("共耗时："+(System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long)httpServletRequest.getAttribute("start");
        System.out.println("共耗时："+(System.currentTimeMillis() - start));
    }
}
