package com.wangcheng.zeus.core.config.validate.code.manager;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.manager.impl.KaptchaValidateCodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


/**
 * @author : Administrator
 * @Date: 2018/9/21 20:36
 * @Description:
 */
@Configuration
@Order(100)
public class ValidateCodeBeanConfig {

    @Autowired
    private ZeusProperties zeusProperties;

    @Bean
    @ConditionalOnMissingBean(ValidateCodeManager.class)
    public ValidateCodeManager validateCodeManager(){
        return new KaptchaValidateCodeManager(zeusProperties,new DefaultKaptcha());
    }
}
