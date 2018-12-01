package com.wangcheng.zeus.core.config.authentication.account;

import com.wangcheng.zeus.core.config.authentication.ZeusAuthenticationFilter;
import com.wangcheng.zeus.core.config.authentication.handle.AuthFailureHandler;
import com.wangcheng.zeus.core.config.authentication.handle.AuthSuccessHandler;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author: evan
 * @date: 2018/11/5 23:05
 * @description:
 */
@Component
public class AccountAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private ZeusProperties zeusProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AccountAuthenticationFilter accountAuthenticationFilter = new AccountAuthenticationFilter(http.getSharedObject(AuthenticationManager.class));
        accountAuthenticationFilter.setAuthenticationFailureHandler(authFailureHandler);
        accountAuthenticationFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        ZeusAuthenticationFilter zeusAuthenticationFilter = new ZeusAuthenticationFilter(zeusProperties,userDetailsService);
        http.authenticationProvider(new AccountAuthenticationProvider(userDetailsService,zeusProperties))
            .addFilterAfter(accountAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(zeusAuthenticationFilter,AnonymousAuthenticationFilter.class);
    }
}
