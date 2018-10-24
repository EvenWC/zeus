package com.wangcheng.zeus.core.config.authentication.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.properties.enums.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
public class ZeusSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ZeusProperties zeusProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(LoginType.JSON.equals(zeusProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResponseModel.SUCCESS(authentication.getPrincipal())));
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }

}
