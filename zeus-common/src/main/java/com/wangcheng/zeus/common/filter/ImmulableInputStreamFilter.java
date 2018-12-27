package com.wangcheng.zeus.common.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: evan
 * @date: 2018/11/14 22:37
 * @description:
 */
public class ImmulableInputStreamFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequestWrapper wrapper = null;

        if(request instanceof HttpServletRequest){
            wrapper = new ImmutableInputStreamServletRequest((HttpServletRequest)request);
        }
        if(Objects.nonNull(wrapper)){
            chain.doFilter(wrapper,response);
        }else{
            chain.doFilter(request,response);
        }
    }
}
