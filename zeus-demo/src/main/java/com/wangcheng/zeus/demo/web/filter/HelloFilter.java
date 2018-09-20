package com.wangcheng.zeus.demo.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/15 12:19
 * @Description:
 */
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HelloFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println("HelloFilter doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("共耗时： " + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        System.out.println("HelloFilter destroy");
    }
}
