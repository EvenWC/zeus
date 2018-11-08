package com.wangcheng.zeus.core.config.social.qq.config;

import com.wangcheng.zeus.core.config.properties.QQProperties;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author: Administrator
 * @date: 2018/10/28 14:23
 * @description:
 */
@Configuration
@ConditionalOnProperty(prefix = "zeus.social.qq",name = "appId")
public class QQAutoConfigurer extends SocialAutoConfigurerAdapter {

    @Autowired
    private ZeusProperties zeusProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = zeusProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
