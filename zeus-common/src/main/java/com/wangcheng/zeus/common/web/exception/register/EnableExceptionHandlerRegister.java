package com.wangcheng.zeus.common.web.exception.register;

import com.wangcheng.zeus.common.web.exception.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author wangcheng
 * @Date: 2018/9/24 09:55
 * @Description: 注册全局异常处理对象
 */
@ComponentScan("com.wangcheng.zeus.common")
public class EnableExceptionHandlerRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        String handlerName = beanNameToLower(GlobalExceptionHandler.class.getSimpleName());

        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableExceptionHandler.class.getName());
        boolean disabled = (boolean)attributes.get("disabled");
        try {
            beanDefinitionRegistry.getBeanDefinition(handlerName);
            if(disabled){
                beanDefinitionRegistry.removeBeanDefinition(handlerName);
            }
        }catch (Exception e){
            if(!disabled){
                BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(GlobalExceptionHandler.class);
                AbstractBeanDefinition beanDefinition = bdb.getBeanDefinition();
                beanDefinitionRegistry.registerBeanDefinition(handlerName,beanDefinition);
            }
        }
    }

    /**
     * 把类名的第一个大写转成小写
     * @param beanName
     * @return
     */
    private String beanNameToLower(String beanName){
        Assert.isTrue(StringUtils.hasText(beanName),"类名不能为空");
        char a = 'A';
        char z = 'Z';
        char sc = beanName.charAt(0);
        if(sc > a && sc < z){
            return (char)(sc+32) + beanName.substring(1);
        }
        return beanName;
    }
}
