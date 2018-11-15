package com.wangcheng.zeus.core.config.authentication.mobile;

import com.wangcheng.zeus.core.config.authentication.handle.ZeusFailureHandler;
import com.wangcheng.zeus.core.config.authentication.handle.ZeusSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author: evan
 * @date: 2018/10/9 21:56
 * @description:
 */
@Component
public class SmsAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ZeusSuccessHandler zeusSuccessHandler;

    @Autowired
    private ZeusFailureHandler zeusFailureHandler;

    @Override
    public void configure(HttpSecurity http)  {

        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        //设置AuthenticationManager
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationFailureHandler(zeusFailureHandler);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(zeusSuccessHandler);
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider(userDetailsService);
        http.authenticationProvider(smsAuthenticationProvider)
            .addFilterAfter(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
