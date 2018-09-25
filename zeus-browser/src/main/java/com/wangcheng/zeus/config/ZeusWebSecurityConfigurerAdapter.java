package com.wangcheng.zeus.config;

import com.wangcheng.zeus.authentication.ZeusFailureHandler;
import com.wangcheng.zeus.authentication.ZeusSuccessHandler;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.ValidateCodeFilter;
import com.wangcheng.zeus.remeberme.RemembermeFilter;
import com.wangcheng.zeus.remeberme.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Auther: Administrator
 * @Date: 2018/9/17 20:36
 * @Description:
 */

@Configuration
public class ZeusWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZeusProperties zeusProperties;

    @Autowired
    private ZeusSuccessHandler zeusSuccessHandler;

    @Autowired
    private ZeusFailureHandler zeusFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter(zeusFailureHandler, zeusProperties);
        RemembermeFilter remembermeFilter = new RemembermeFilter(userDetailsService,tokenManager);
        validateCodeFilter.afterPropertiesSet();
        http.csrf().disable().cors().and()
             .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
            .formLogin().loginPage("/authentication/strategy").loginProcessingUrl("/authentication/logIn")
                .successHandler(zeusSuccessHandler)
                .failureHandler(zeusFailureHandler)
                .and()
            .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(zeusProperties.getBrowser().getRememeberMeExpireIn())
            .and()
                .authorizeRequests()
                .antMatchers("/authentication/strategy").permitAll()
                .antMatchers("/code/image").permitAll()
                .antMatchers(zeusProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
            .and();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
       // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
