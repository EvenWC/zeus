package com.wangcheng.zeus.core.config.authentication.account;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.core.config.properties.BrowserProperties;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: evan
 * @date: 2018/11/5 22:25
 * @description:
 */
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private ZeusProperties zeusProperties;

    public AccountAuthenticationProvider(UserDetailsService userDetailsService, ZeusProperties zeusProperties){
        this.userDetailsService = userDetailsService;
        this.zeusProperties = zeusProperties;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountAuthenticationToken token = (AccountAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if(userDetails == null){
            throw new UsernameNotFoundException("用户名没有找到");
        }
        //todo:密码先暂时直接判断，后续可能会做加密处理
        if(!userDetails.getPassword().equals(token.getCredentials())){
            throw new BadCredentialsException("用户名或密码错误");
        }
        //todo:添加记住我功能

        //生成token，传给前端
        Map<String,Object> claims = Maps.newHashMapWithExpectedSize(3);
        claims.put("username",userDetails.getUsername());
        //暂时写死一个
        claims.put("sessionId","session");
        BrowserProperties browser = zeusProperties.getBrowser();
        String accessToken = TokenUtils.generateJwtToken(claims, browser.getSecret(), browser.getRememeberMeExpireIn());
        AccountAuthenticationToken authenticationToken = new AccountAuthenticationToken(token.getPrincipal(), token.getCredentials(), userDetails.getAuthorities());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccessToken(accessToken);
        loginInfo.setUsername(userDetails.getUsername());
        authenticationToken.setDetails(loginInfo);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AccountAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
