package com.wangcheng.zeus.core.config.validate.code;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.core.config.constant.ZeusSecurityConstants;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.exception.ValidateCodeException;
import com.wangcheng.zeus.core.config.validate.code.processor.ValidateCodeProcessorHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author : Administrator
 * @Date: 2018/9/20 21:33
 * @Description: 验证码拦截器
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 短信验证码默认校验路径
     */
    private static final String SMS_DEFAULT_AUTHENTICATION_PATH   = ZeusSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE;
    /**
     * 图片验证码默认校验路径
     */
    private static final String IMAGE_DEFAULT_AUTHENTICATION_PATH = ZeusSecurityConstants. ACCOUNT_LOGIN_PROCESSING_URL_FORM;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationFailureHandler handler;

    private Map<String,ValidateCodeType> urlMap  = Maps.newHashMap();

    private ZeusProperties zeusProperties;

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
        addUrlToMap(IMAGE_DEFAULT_AUTHENTICATION_PATH,ValidateCodeType.IMAGE);
        addUrlToMap(imageUrls,ValidateCodeType.IMAGE);

        String smsUrls = zeusProperties.getValidateCode().getSmsCode().getUrls();
        addUrlToMap(SMS_DEFAULT_AUTHENTICATION_PATH,ValidateCodeType.SMS);
        addUrlToMap(smsUrls,ValidateCodeType.SMS);

    }

    protected void addUrlToMap(String urls,ValidateCodeType validateCodeType){
        if(StringUtils.isNotEmpty(urls)){
            String[] urlArr = StringUtils.splitPreserveAllTokens(urls, ",");
            for (String url : urlArr) {
                urlMap.put(url,validateCodeType);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
