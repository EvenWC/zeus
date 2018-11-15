package com.wangcheng.zeus.core.config.social.qq.connect;

import com.wangcheng.zeus.core.config.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: evan
 * @date: 2018/10/27 15:36
 * @description:
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
