package com.wangcheng.zeus.common.configuration;

import com.wangcheng.zeus.common.web.exception.properties.ExceptionHandlerProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : Administrator
 * @Date: 2018/9/26 21:52
 * @Description:
 */
@ComponentScan("com.wangcheng.zeus.common")
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
@AutoConfigureAfter(StrategyHolderAutoConfiguration.class)
public class EnableExceptionHandleAutoConfiguration {

}
