package com.wangcheng.zeus.core.config.validate.code;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.exception.ValidateCodeException;
import com.wangcheng.zeus.core.config.validate.code.processor.ValidateCodeProcessor;
import com.wangcheng.zeus.core.config.validate.code.processor.ValidateCodeProcessorHolder;
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
import java.util.Map;
import java.util.Set;

/**
 * @author : Administrator
 * @Date: 2018/9/20 21:33
 * @Description: 验证码拦截器
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationFailureHandler handler;

    private Map<String,ValidateCodeType> urlMap  = Maps.newHashMap();

    private ZeusProperties zeusProperties;

    private PathMatcher pathMatcher = new AntPathMatcher();

    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    public ValidateCodeFilter(AuthenticationFailureHandler handler, ZeusProperties zeusProperties, ValidateCodeProcessorHolder validateCodeProcessorHolder){
        this.handler = handler;
        this.zeusProperties = zeusProperties;
        this.validateCodeProcessorHolder = validateCodeProcessorHolder;
    }

    @Override
    public void afterPropertiesSet(){
        String imageUrls = zeusProperties.getValidateCode().getImageCode().getUrls();
        //添加默认需要拦截的url
        addUrlToMap(ZeusConstans.IMAGE_DEFAULT_AUTHENTICATION_PATH,ValidateCodeType.IMAGE);
        addUrlToMap(imageUrls,ValidateCodeType.IMAGE);

        String smsUrls = zeusProperties.getValidateCode().getSmsCode().getUrls();
        addUrlToMap(ZeusConstans.SMS_DEFAULT_AUTHENTICATION_PATH,ValidateCodeType.SMS);
        addUrlToMap(smsUrls,ValidateCodeType.SMS);

    }

    protected void addUrlToMap(String urls,ValidateCodeType validateCodeType){
        if(StringUtils.isNotEmpty(urls)){
            String[] urlArr = StringUtils.splitPreserveAllTokens(urls, ",");
            for (String url :urlArr) {
                urlMap.put(url,validateCodeType);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("用户请求路径：{},用户请求方式:{}",request.getRequestURI(),request.getMethod());
        ValidateCodeType validateCodeType = urlMap.get(request.getRequestURI());
        //判断是否需要验证
        if(validateCodeType != null){
            //获取要执行的
            try {

                validateCodeProcessorHolder.getValidateCodeProcessorByType(validateCodeType).validateCode(new ServletWebRequest(request));

            }catch (ValidateCodeException e){
                handler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        filterChain.doFilter(request,response);
    }




}
