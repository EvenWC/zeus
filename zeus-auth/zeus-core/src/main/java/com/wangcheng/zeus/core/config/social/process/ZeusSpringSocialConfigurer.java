package com.wangcheng.zeus.core.config.social.process;

import com.wangcheng.zeus.core.config.authentication.handle.AuthFailureHandler;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.social.process.handler.SocialAuthFailureHandler;
import com.wangcheng.zeus.core.config.social.process.handler.SocialAuthSuccessHandler;
import com.wangcheng.zeus.core.config.social.process.provider.ZeusSocialAuthenticationProvider;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.*;

/**
 * @author: even
 * @date: 2018/12/13 22:09
 * @description:
 */
public class ZeusSpringSocialConfigurer extends SpringSocialConfigurer {

    private ZeusProperties zeusProperties;

    public ZeusSpringSocialConfigurer( ZeusProperties zeusProperties){
        this.zeusProperties = zeusProperties;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        UsersConnectionRepository usersConnectionRepository = getDependency(applicationContext, UsersConnectionRepository.class);
        SocialAuthenticationServiceLocator authServiceLocator = getDependency(applicationContext, SocialAuthenticationServiceLocator.class);
        SocialUserDetailsService socialUsersDetailsService = getDependency(applicationContext, SocialUserDetailsService.class);
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(
                http.getSharedObject(AuthenticationManager.class),
                new AuthenticationNameUserIdSource(),
                usersConnectionRepository,
                authServiceLocator);
        SocialAuthFailureHandler failureHandler = new SocialAuthFailureHandler(new AuthFailureHandler());
        SocialAuthSuccessHandler successHandler = new SocialAuthSuccessHandler(zeusProperties.getSocial().getCallbackFrontUrl());
        successHandler.afterPropertiesSet();
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setFilterProcessesUrl(zeusProperties.getSocial().getFilterProcessesUrl());
        http.authenticationProvider(
                new ZeusSocialAuthenticationProvider(usersConnectionRepository, socialUsersDetailsService,zeusProperties))
                .addFilterBefore(postProcess(filter), AbstractPreAuthenticatedProcessingFilter.class);
    }
    private <T> T getDependency(ApplicationContext applicationContext, Class<T> dependencyType) {
        try {
            T dependency = applicationContext.getBean(dependencyType);
            return dependency;
        } catch (NoSuchBeanDefinitionException e) {
            throw new IllegalStateException("SpringSocialConfigurer depends on " + dependencyType.getName() +". No single bean of that type found in application context.", e);
        }
    }
}
