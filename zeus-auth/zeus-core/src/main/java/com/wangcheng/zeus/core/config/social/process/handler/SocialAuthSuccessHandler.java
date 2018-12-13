package com.wangcheng.zeus.core.config.social.process.handler;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.common.cache.CacheService;
import com.wangcheng.zeus.common.websocket.server.WebSocketServer;
import com.wangcheng.zeus.core.config.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author: evan
 * @date: 2018/11/20 22:21
 * @description:
 */
public class SocialAuthSuccessHandler implements AuthenticationSuccessHandler {

    private CacheService cacheService;

    public SocialAuthSuccessHandler(CacheService cacheService){
        this.cacheService = cacheService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Session session = WebSocketServer.sessions.get(request.getParameter("state"));
        if(Objects.nonNull(session)){
            session.getBasicRemote().sendText(JsonUtils.writeValueAsString(authentication.getDetails()));
        }else{
            HashMap<String, String> map = Maps.newHashMap();
            map.put("sessionId",request.getParameter("state"));
            map.put("authentication",JsonUtils.writeValueAsString(authentication.getDetails()));
            cacheService.pub(map);
        }
    }
}
