package com.wangcheng.zeus.core.config.authentication.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangcheng.zeus.core.config.authentication.constant.ZeusSecurityConstants;
import com.wangcheng.zeus.core.config.authentication.mobile.SmsAuthenticationToken;
import com.wangcheng.zeus.core.config.utils.JsonUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: evan
 * @date: 2018/11/5 20:49
 * @description:
 */
public class AccountAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private static final String PARAMETER_ACCOUNT_KEY = "account";

    private static final String PARAMETER_PASSWORD_KEY = "password";

    private static final String PARAMETER_REMEMBERME_KEY = "rememberme";


    protected AccountAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(ZeusSecurityConstants.ACCOUNT_LOGIN_PROCESSING_URL_FORM , HttpMethod.POST.name()));
        //设置authenticationManager
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        ServletInputStream is = request.getInputStream();
        AccountAuthenticationToken authenticationToken;
        try {
            authenticationToken = new AccountAuthenticationToken(JsonUtils.readValue(is, AccountAuthentication.class));
        } catch (IOException e) {
             //尝试从request 请求参数中获取
            String account = request.getParameter(PARAMETER_ACCOUNT_KEY);
            String password = request.getParameter(PARAMETER_PASSWORD_KEY);
            String rememberme = request.getParameter(PARAMETER_REMEMBERME_KEY);
            Boolean remembermeBoolean = "true".equals(rememberme);
            authenticationToken = new AccountAuthenticationToken(account,password,remembermeBoolean);
        }
        setDetails(request,authenticationToken);
        return super.getAuthenticationManager().authenticate(authenticationToken);
    }

    /**
     * 设置detail
     * @param request
     * @param accountAuthenticationToken
     */
    protected void setDetails(HttpServletRequest request,
                              AccountAuthenticationToken accountAuthenticationToken) {
        accountAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
