package com.wangcheng.zeus.core.config.social.process.handler;

/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.security.SocialAuthenticationRedirectException;

/**
 * @author Craig Walls
 */
public class SocialAuthFailureHandler implements AuthenticationFailureHandler {

    private AuthenticationFailureHandler delegate;

    public SocialAuthFailureHandler(AuthenticationFailureHandler delegate) {
        this.delegate = delegate;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //如果是这个异常表示，用户没有跳转到指定的登陆页面进行登陆，因此重定向到登陆页面
        if (failed instanceof SocialAuthenticationRedirectException) {
            String redirectUrl = ((SocialAuthenticationRedirectException) failed).getRedirectUrl();
            response.sendRedirect(redirectUrl);
            return;
        }
        delegate.onAuthenticationFailure(request, response, failed);
    }
}
