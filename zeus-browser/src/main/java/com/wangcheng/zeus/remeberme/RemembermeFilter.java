package com.wangcheng.zeus.remeberme;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Administrator
 * @Date: 2018/9/25 20:30
 * @Description: 记住我过滤器
 */
public class RemembermeFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    private TokenManager tokenManager;

    public RemembermeFilter(UserDetailsService userDetailsService,TokenManager tokenManager){
        this.tokenManager = tokenManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从cookie中获取token，如果没有，那么说明是登录

    }
}
