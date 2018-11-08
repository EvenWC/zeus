package com.wangcheng.zeus.core.config.validate.code.config;

import com.wangcheng.zeus.core.config.authentication.handle.ZeusFailureHandler;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.ValidateCodeFilter;
import com.wangcheng.zeus.core.config.validate.code.processor.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 验证码security 配置
 * @author: evan
 * @date: 2018/10/11 20:48
 * @description:
 */
@Configuration
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    @Autowired
    private ZeusFailureHandler zeusFailureHandler;
    @Autowired
    private ZeusProperties zeusProperties;
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter(zeusFailureHandler, zeusProperties,validateCodeProcessorHolder);
        validateCodeFilter.afterPropertiesSet();
        http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
