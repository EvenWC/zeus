package com.wangcheng.zeus.core.config.authentication.handle;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.utils.HttpResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/19 21:34
 * @Description:
 */
@Component("zeusSuccessHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpResponseUtils.printJson(response,ResponseModel.SUCCESS(authentication.getDetails()));
    }
}
