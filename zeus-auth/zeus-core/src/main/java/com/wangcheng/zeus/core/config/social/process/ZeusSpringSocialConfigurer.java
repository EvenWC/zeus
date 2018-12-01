package com.wangcheng.zeus.core.config.social.process;

import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.social.process.filter.ZeusSocialAuthenticationFilter;
import com.wangcheng.zeus.core.config.social.process.provider.ZeusSocialAuthenticationProvider;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.*;

/**
 * @author: evan
 * @date: 2018/11/19 21:41
 * @description:
 */
public class ZeusSpringSocialConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>  {

    private ZeusProperties zeusProperties;

    private UserIdSource userIdSource;

    private String postLoginUrl;

    private String postFailureUrl;

    private String signupUrl;

    private String connectionAddedRedirectUrl;

    private String defaultFailureUrl;

    private boolean alwaysUsePostLoginUrl = false;

    /**
     * Constructs a SpringSocialHttpConfigurer.
     * Requires that {@link UsersConnectionRepository}, {@link SocialAuthenticationServiceLocator}, and
     * {@link SocialUserDetailsService} beans be available in the application context.
     */
    public ZeusSpringSocialConfigurer(ZeusProperties zeusProperties) {
        this.zeusProperties = zeusProperties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        UsersConnectionRepository usersConnectionRepository = getDependency(applicationContext, UsersConnectionRepository.class);
        SocialAuthenticationServiceLocator authServiceLocator = getDependency(applicationContext, SocialAuthenticationServiceLocator.class);
        SocialUserDetailsService socialUsersDetailsService = getDependency(applicationContext, SocialUserDetailsService.class);
        ZeusSocialAuthenticationFilter filter = new ZeusSocialAuthenticationFilter(
                http.getSharedObject(AuthenticationManager.class),
                userIdSource != null ? userIdSource : new AuthenticationNameUserIdSource(),
                usersConnectionRepository,
                authServiceLocator);

        RememberMeServices rememberMe = http.getSharedObject(RememberMeServices.class);
        if (rememberMe != null) {
            filter.setRememberMeServices(rememberMe);
        }

        if (postLoginUrl != null) {
            filter.setPostLoginUrl(postLoginUrl);
            filter.setAlwaysUsePostLoginUrl(alwaysUsePostLoginUrl);
        }

        if (postFailureUrl != null) {
            filter.setPostFailureUrl(postFailureUrl);
        }

        if (signupUrl != null) {
            filter.setSignupUrl(signupUrl);
        }

        if (connectionAddedRedirectUrl != null) {
            filter.setConnectionAddedRedirectUrl(connectionAddedRedirectUrl);
        }

        if (defaultFailureUrl != null) {
            filter.setDefaultFailureUrl(defaultFailureUrl);
        }
        http.authenticationProvider(
                new ZeusSocialAuthenticationProvider(usersConnectionRepository, socialUsersDetailsService,zeusProperties))
                .addFilterBefore(postProcess(filter), AbstractPreAuthenticatedProcessingFilter.class);
    }

    private <T> T getDependency(ApplicationContext applicationContext, Class<T> dependencyType) {
        try {
            T dependency = applicationContext.getBean(dependencyType);
            return dependency;
        } catch (NoSuchBeanDefinitionException e) {
            throw new IllegalStateException("ZeusSpringSocialConfigurer depends on " + dependencyType.getName() +". No single bean of that type found in application context.", e);
        }
    }

    /**
     * Sets the {@link UserIdSource} to use for authentication. Defaults to {@link AuthenticationNameUserIdSource}.
     * @param userIdSource the UserIdSource to use when authenticating
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer userIdSource(UserIdSource userIdSource) {
        this.userIdSource = userIdSource;
        return this;
    }

    /**
     * Sets the URL to land on after a successful login.
     * @param postLoginUrl the URL to redirect to after a successful login
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer postLoginUrl(String postLoginUrl) {
        this.postLoginUrl = postLoginUrl;
        return this;
    }

    /**
     * If true, always redirect to postLoginUrl, even if a pre-signin target is in the request cache.
     * @param alwaysUsePostLoginUrl if true, always redirect to the postLoginUrl
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer alwaysUsePostLoginUrl(boolean alwaysUsePostLoginUrl) {
        this.alwaysUsePostLoginUrl = alwaysUsePostLoginUrl;
        return this;
    }

    /**
     * Sets the URL to land on after a failed login.
     * @param postFailureUrl the URL to redirect to after a failed login
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer postFailureUrl(String postFailureUrl) {
        this.postFailureUrl = postFailureUrl;
        return this;
    }

    /**
     * Sets the URL to land on after an authentication failure so that the user can register with the application.
     * @param signupUrl the URL to redirect to after an authentication failure
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer signupUrl(String signupUrl) {
        this.signupUrl = signupUrl;
        return this;
    }

    /**
     * Sets the URL to land on after an a connection was added.
     * @param connectionAddedRedirectUrl the URL to redirect after a connection was added
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer connectionAddedRedirectUrl(String connectionAddedRedirectUrl) {
        this.connectionAddedRedirectUrl = connectionAddedRedirectUrl;
        return this;
    }

    /**
     * Sets the URL to redirect to if authentication fails or if authorization is denied by the user.
     * @param defaultFailureUrl the URL to redirect to after an authentication fail or authorization deny
     * @return this ZeusSpringSocialConfigurer for chained configuration
     */
    public ZeusSpringSocialConfigurer defaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
        return this;
    }
}
