package com.wangcheng.zeus.config;

import com.wangcheng.zeus.core.config.authentication.constant.ZeusSecurityConstants;
import com.wangcheng.zeus.core.config.authentication.handle.ZeusFailureHandler;
import com.wangcheng.zeus.core.config.authentication.handle.ZeusSuccessHandler;
import com.wangcheng.zeus.core.config.authentication.mobile.AuthenticationMobileConfig;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.config.ValidateCodeSecurityConfig;
import com.wangcheng.zeus.core.config.validate.code.processor.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author : Administrator
 * @Date: 2018/9/17 20:36
 * @Description:
 */

@Configuration
public class ZeusWebSecurityConfigurerAdapter extends AbstractWebSecurityConfig {

    @Autowired
    private ZeusProperties zeusProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationMobileConfig authenticationMobileConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //密码登录相关
        applyPasswordAuthenticationConfig(http);

        http
            .apply(validateCodeSecurityConfig)
            .and()
            .apply(authenticationMobileConfig).and()
            .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(zeusProperties.getBrowser().getRememeberMeExpireIn())
            .and()
                .authorizeRequests()
                .antMatchers(ZeusSecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        ZeusSecurityConstants.VALIDATE_CODE_URL_PREFIX + "/*",
                        zeusProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
            .csrf().disable().cors().and();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
