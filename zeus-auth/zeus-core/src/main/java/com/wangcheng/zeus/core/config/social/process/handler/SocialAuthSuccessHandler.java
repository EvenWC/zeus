package com.wangcheng.zeus.core.config.social.process.handler;

import com.wangcheng.zeus.core.config.social.websocket.server.WebSocketServer;
import com.wangcheng.zeus.core.config.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;

/**
 * @author: evan
 * @date: 2018/11/20 22:21
 * @description:
 */
public class SocialAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Session session = WebSocketServer.sessions.get(request.getParameter("state"));
        session.getBasicRemote().sendText(JsonUtils.writeValueAsString(authentication.getDetails()));
    }
}
