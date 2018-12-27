package com.wangcheng.zeus.common.cache.config;

import com.wangcheng.zeus.common.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Administrator
 * @date: 2018/12/13 22:54
 * @description:
 */
@Component
public class MessageReceiver {

    /**接收消息的方法*/
    public void receiveMessage(Object object) throws IOException, EncodeException {
        //handle object
        Map<String,String> map = (Map) object;
        String sessionId = map.get("sessionId");
        Session session = WebSocketServer.sessions.get((sessionId));
        if(Objects.nonNull(session)){
            session.getBasicRemote().sendText(map.get("authentication"));
        }
    }
}
