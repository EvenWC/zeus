package com.wangcheng.zeus.common.configuration;

import com.wangcheng.zeus.common.web.exception.holder.ExceptionHandleStrategyHolder;
import com.wangcheng.zeus.common.web.exception.holder.impl.BaseSpringExceptionHandleStrategyHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Administrator
 * @Date: 2018/9/29 20:09
 * @Description:
 */
@Configuration
public class StrategyHolderAutoConfiguration {
    @Bean
    public ExceptionHandleStrategyHolder exceptionHandleStrategyHolder(){
        return new BaseSpringExceptionHandleStrategyHolder();
    }
}
