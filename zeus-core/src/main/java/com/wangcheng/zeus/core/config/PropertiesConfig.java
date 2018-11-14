package com.wangcheng.zeus.core.config;

import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Administrator
 * @date : 2018/9/18 22:59
 */
@Configuration
@EnableConfigurationProperties(ZeusProperties.class)
public class PropertiesConfig {

}
