package com.wangcheng.zeus.core.config.social.process.handler;

import com.wangcheng.zeus.common.cache.CacheService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: evan
 * @date: 2018/11/20 22:21
 * @description:
 */
public class SocialAuthSuccessHandler implements AuthenticationSuccessHandler {

    private CacheService cacheService;

    public SocialAuthSuccessHandler(CacheService cacheService){
        this.cacheService = cacheService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.setAttribute("auth",authentication.getDetails());
        request.getRequestDispatcher("/success.do").forward(request,response);
    }
}
