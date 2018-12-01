package com.wangcheng.zeus.core.config.social.websocket.server;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.core.config.social.websocket.config.MapEncoder;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author: Administrator
 * @date: 2018/11/22 23:22
 * @description:
 */
@Component
@ServerEndpoint(value = "/websocket")
public class  WebSocketServer {

    public static final Map<String,Session> sessions = Maps.newHashMap();

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId,session);
        try {
            session.getBasicRemote().sendText(sessionId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        Set<String> keySet = sessions.keySet();

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        sessions.forEach((k,v)->{
            if(v.equals(session)){
                try {
                    session.getBasicRemote().sendText(k);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 发生错误时调用
     * */
    @OnError
    public void onError(Session session, Throwable error) {

    }
}