package com.wangcheng.zeus.core.config.social.process.handler;

import com.wangcheng.zeus.core.config.authentication.account.LoginInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * @author: evan
 * @date: 2018/11/20 22:21
 * @description:
 */
public class SocialAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.setAttribute("auth",authentication.getDetails());
        response.sendRedirect("http://localhost:4444/#/success"+tokenSerializable((LoginInfo)authentication.getDetails()));
    }


    private String tokenSerializable(LoginInfo token){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        //String serializa = "?accessToken="+token.getAccessToken()+"&username="+token.getUsername()+"&localDateTime="+df.format(token.getLocalDateTime());
        String accessTokenParam = "?accessToken="+token.getAccessToken();
        return accessTokenParam;
    }

}
