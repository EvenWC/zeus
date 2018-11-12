package com.wangcheng.zeus.config;

import com.wangcheng.zeus.core.config.authentication.account.AccountAuthenticationConfigurer;
import com.wangcheng.zeus.core.config.authentication.mobile.SmsAuthenticationConfigurer;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author : evan
 * @Date: 2018/9/17 20:36
 * @Description:
 */

@Configuration
public class ZeusWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZeusProperties zeusProperties;

    @Autowired
    private SmsAuthenticationConfigurer smsAuthenticationConfigurer;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    private AccountAuthenticationConfigurer accountAuthenticationConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
            .apply(validateCodeSecurityConfig)
                .and()
            //自定义密码登录相关
            .apply(accountAuthenticationConfigurer)
                .and()
            .apply(smsAuthenticationConfigurer)
                .and()
            .apply(springSocialConfigurer)
                .and()
                .authorizeRequests()
                .antMatchers(zeusProperties.getExcludeURIs())
                .permitAll()
                .anyRequest()
                .authenticated()
            .and();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.parentAuthenticationManager(null);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
