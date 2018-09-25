/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wangcheng.zeus.common.web.exception.handler;

import com.google.common.collect.Sets;
import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandleType;
import com.wangcheng.zeus.common.web.exception.strategy.impl.BusinessExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.impl.DefaultExceptionHandleStrategy;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author wangcheng
 */
@RestControllerAdvice
public class GlobalExceptionHandler implements ApplicationContextAware {

    private Set<ExceptionHandleStrategy> strategies = Sets.newHashSet();

    private DefaultExceptionHandleStrategy defaultExceptionHandleStrategy = new DefaultExceptionHandleStrategy();

    private BusinessExceptionHandleStrategy businessExceptionHandleStrategy = new BusinessExceptionHandleStrategy();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> strategyMap = applicationContext.getBeansWithAnnotation(ExceptionHandleType.class);
        strategyMap.forEach((k,v)->{
            if(v instanceof ExceptionHandleStrategy){
                strategies.add((ExceptionHandleStrategy)v);
            }else {
                throw new RuntimeException("异常策略对象必须实现ExceptionHandleStrategy接口");
            }
        });
        //默认注入业务异常处理策略和默认的异常处理策略
        strategies.add(defaultExceptionHandleStrategy);
        strategies.add(businessExceptionHandleStrategy);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseModel handleException(Exception e){
        ExceptionHandleStrategy exceptionHandler = this.getExceptionHandleByException(e);
        return exceptionHandler.handle(e);
    }

    private ExceptionHandleStrategy getExceptionHandleByException(Exception e){
        for (ExceptionHandleStrategy strategy : strategies) {
            ExceptionHandleType annotation = strategy.getClass().getAnnotation(ExceptionHandleType.class);
            Class<? extends Exception>[] value = annotation.value();
            long count = Arrays.stream(value).filter(clz -> clz.equals(e.getClass())).count();
            if(count > 0){
                return strategy;
            }
        }
        return defaultExceptionHandleStrategy;
    }

}
