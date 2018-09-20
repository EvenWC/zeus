package com.wangcheng.zeus.core.config.validate.code;

import com.wangcheng.zeus.core.config.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/20 21:33
 * @Description: 验证码拦截器
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SessionStrategy  sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler handler;

    public ValidateCodeFilter(AuthenticationFailureHandler handler){
        this.handler = handler;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/authentication/logIn",request.getRequestURI()) && StringUtils.equalsIgnoreCase(HttpMethod.POST.name(),request.getMethod())){
            try{
                validate(new ServletWebRequest(request));
            }catch(ValidateCodeException exception){
                handler.onAuthenticationFailure(request,response,exception);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //从session中获取验证码
        ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(servletWebRequest, ValidateCodeControl.SESSION_KEY);
        //从请求中获取验证码
        String requestCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if(StringUtils.isEmpty(requestCode)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpire()){
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equalsIgnoreCase(codeInSession.getCode(),requestCode)){
            throw new ValidateCodeException("验证码不正确");
        }
        //从session中清除验证码
        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeControl.SESSION_KEY);
    }


}
