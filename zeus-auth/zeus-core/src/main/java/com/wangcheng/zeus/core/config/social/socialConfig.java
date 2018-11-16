package com.wangcheng.zeus.core.config.social;

import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.social.qq.ZeusSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author: evan
 * @date: 2018/10/27 15:42
 * @description:
 */
@Configuration
@EnableSocial
public class socialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ZeusProperties zeusProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText());
        return jdbcUsersConnectionRepository;
    }
    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
        return new ZeusSpringSocialConfigurer(zeusProperties.getSocial().getFilterProcessesUrl());
    }
}
