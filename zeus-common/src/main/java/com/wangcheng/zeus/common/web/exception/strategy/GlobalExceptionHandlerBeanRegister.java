package com.wangcheng.zeus.common.web.exception.strategy;

import com.wangcheng.zeus.common.web.exception.handler.GlobalExceptionHandler;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.EnableExceptionHandle;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import java.util.Map;

/**
 * @author wangcheng
 * @Date: 2018/9/24 09:55
 * @Description: 注册全局异常处理对象
 */
public class GlobalExceptionHandlerBeanRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, Object> vMap = annotationMetadata.getAnnotationAttributes(EnableExceptionHandle.class.getName());
        vMap.forEach((k,v)->{
            System.out.println("k--->"+k+"\nv--------->"+v);
        });
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(GlobalExceptionHandler.class);
        AbstractBeanDefinition beanDefinition = bdb.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("globalExceptionHandler",beanDefinition);
    }
}
