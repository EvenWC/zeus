package com.wangcheng.zeus.core.config.validate.code;

import com.google.common.collect.Sets;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @Auther: Administrator
 * @Date: 2018/9/20 21:33
 * @Description: 验证码拦截器
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SessionStrategy  sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler handler;

    private Set<String> urlSet  = Sets.newHashSet();

    private ZeusProperties zeusProperties;

    private PathMatcher pathMatcher = new AntPathMatcher();

    public ValidateCodeFilter(AuthenticationFailureHandler handler, ZeusProperties zeusProperties){
        this.handler = handler;
        this.zeusProperties = zeusProperties;
    }

    @Override
    public void afterPropertiesSet(){
        String urlString = zeusProperties.getValidateCode().getImageCode().getUrls();
        String[] urls = StringUtils.splitPreserveAllTokens(urlString, ",");
        for (String url :urls) {
            urlSet.add(url);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("用户请求路径：{},用户请求方式:{}",request.getRequestURI(),request.getMethod());
        Boolean action = false;
        for (String url: urlSet) {
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
                break;
            }
        }
        if(action){
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
