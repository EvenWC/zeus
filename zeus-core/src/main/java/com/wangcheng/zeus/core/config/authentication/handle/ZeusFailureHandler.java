package com.wangcheng.zeus.core.config.authentication.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.properties.enums.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
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
public class ZeusFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ZeusProperties zeusProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       if(LoginType.JSON.equals(zeusProperties.getBrowser().getLoginType())){
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.setContentType("application/json;charset=UTF-8");
           response.getWriter().write(objectMapper.writeValueAsString(ResponseModel.FAIL(exception.getMessage())));
       }else {
           super.onAuthenticationFailure(request,response,exception);
       }
    }
}
