package com.wangcheng.zeus.core.config.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Objects;

/**
 * @author: Administrator
 * @date: 2018/10/9 21:21
 * @description:
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public SmsAuthenticationProvider(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        if(Objects.isNull(userDetails)){
            throw new UsernameNotFoundException("手机号不存在");
        }
        SmsAuthenticationToken resultSmsAuthentication = new SmsAuthenticationToken(userDetails,userDetails.getAuthorities());
        //把details信息保存到已认证的Authentication
        resultSmsAuthentication.setDetails(authentication.getDetails());
        return resultSmsAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
