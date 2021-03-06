package com.wangcheng.zeus.core.config.authentication.handle;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.utils.HttpResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Administrator
 * @Date: 2018/9/19 21:36
 * @Description:
 */

@Component("zeusFailureHandler")
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpResponseUtils.printJson(response,ResponseModel.ERROR(exception.getMessage()));
    }
}
