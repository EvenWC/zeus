package com.wangcheng.zeus.config;

import com.wangcheng.zeus.core.config.authentication.constant.ZeusSecurityConstants;
import com.wangcheng.zeus.core.config.authentication.handle.ZeusFailureHandler;
import com.wangcheng.zeus.core.config.authentication.handle.ZeusSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: Administrator
 * @date: 2018/10/11 21:01
 * @description:
 */
public abstract class AbstractWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZeusSuccessHandler zeusSuccessHandler;

    @Autowired
    private ZeusFailureHandler zeusFailureHandler;

    public void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage(ZeusSecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(ZeusSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
            .successHandler(zeusSuccessHandler)
            .failureHandler(zeusFailureHandler);
    }


}
