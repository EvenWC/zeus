package com.wangcheng.zeus.core.config.social.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangcheng.zeus.core.config.authentication.account.LoginInfo;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018/11/23 00:14
 * @description:
 */

public class MapEncoder implements Encoder.Text<LoginInfo>{

    @Override
    public void init(EndpointConfig config) {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    @Override
    public String encode(LoginInfo loginInfo) throws EncodeException {
        ObjectMapper mapMapper = new ObjectMapper();
        try {
            return  mapMapper.writeValueAsString(loginInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
