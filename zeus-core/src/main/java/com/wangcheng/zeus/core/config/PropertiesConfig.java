package com.wangcheng.zeus.core.config;

import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 22:59
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(ZeusProperties.class)
public class PropertiesConfig {

}
