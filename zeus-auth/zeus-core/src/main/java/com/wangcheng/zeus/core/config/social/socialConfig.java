package com.wangcheng.zeus.core.config.social;

import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.social.process.ZeusSpringSocialConfigurer;
import com.wangcheng.zeus.core.config.social.process.repository.ZeusUserConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import javax.sql.DataSource;
import java.util.Objects;

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

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        ZeusUserConnectionRepository zeusUserConnectionRepository = new ZeusUserConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText());
        if(Objects.nonNull(connectionSignUp)){
            zeusUserConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return zeusUserConnectionRepository;
    }
    @Bean
    public ZeusSpringSocialConfigurer springSocialConfigurer(){
        return new ZeusSpringSocialConfigurer(zeusProperties);
    }
}
