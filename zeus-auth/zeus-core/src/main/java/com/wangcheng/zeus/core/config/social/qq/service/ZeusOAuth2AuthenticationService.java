package com.wangcheng.zeus.core.config.social.qq.service;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

/**
 * @author: Administrator
 * @date: 2018/11/22 21:46
 * @description:
 */
public class ZeusOAuth2AuthenticationService extends OAuth2AuthenticationService {


    public ZeusOAuth2AuthenticationService(OAuth2ConnectionFactory connectionFactory) {

        super(connectionFactory);


    }

}
