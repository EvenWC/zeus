package com.wangcheng.zeus.core.config.authentication;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.authentication.account.AccountAuthenticationToken;
import com.wangcheng.zeus.core.config.authentication.account.LoginInfo;
import com.wangcheng.zeus.core.config.properties.BrowserProperties;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.utils.HttpResponseUtils;
import com.wangcheng.zeus.core.config.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  拦截器
 * @author: Administrator
 * @date: 2018/11/6 21:18
 */
public class ZeusAuthenticationFilter extends OncePerRequestFilter {


    private ZeusProperties zeusProperties;

    private UserDetailsService userDetailsService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public ZeusAuthenticationFilter(ZeusProperties zeusProperties,UserDetailsService userDetailsService){
        this.zeusProperties = zeusProperties;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String[] excludeURIs = zeusProperties.getExcludeURIs();
        for (String uri :excludeURIs) {
            if(antPathMatcher.match(uri,requestURI)){
                filterChain.doFilter(request,response);
                return;
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            filterChain.doFilter(request,response);
            return;
        }
        String token = TokenUtils.getToken(request);
        BrowserProperties browser = zeusProperties.getBrowser();
        if(StringUtils.isEmpty(token)){
            HttpResponseUtils.printJson(response,ResponseModel.ACCESS_FORBIDDEN("未登陆"));
            return;
        }
        Claims claims;
        try {
            claims = TokenUtils.parseClaims(token, browser.getSecret());
        } catch (ExpiredJwtException e) {
            HttpResponseUtils.printJson(response,ResponseModel.ACCESS_FORBIDDEN("登录过期"));
            return;
        }
        String username = claims.get("username", String.class);
        if(StringUtils.isEmpty(username)){
            HttpResponseUtils.printJson(response,ResponseModel.ACCESS_FORBIDDEN("非法请求"));
            return;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        AccountAuthenticationToken authenticationToken = new AccountAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccessToken(token);
        loginInfo.setUsername(userDetails.getUsername());
        authenticationToken.setDetails(loginInfo);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
