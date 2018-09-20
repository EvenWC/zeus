package com.wangcheng.zeus.demo.web.config;

import com.wangcheng.zeus.demo.web.filter.HelloFilter;
import com.wangcheng.zeus.demo.web.interceptor.HelloInterceptor;
import com.wangcheng.zeus.demo.web.interceptor.MyCallableProcessingInterceptor;
import com.wangcheng.zeus.demo.web.interceptor.MyDeferredResultProcessingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/15 12:33
 * @Description:
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HelloInterceptor helloInterceptor;

    @Autowired
    private MyCallableProcessingInterceptor callableProcessingInterceptor;

    @Autowired
    private MyDeferredResultProcessingInterceptor deferredResultProcessingInterceptor;

    //异步配置
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        //拦截使用Callable进行异步编程时进行拦截
        configurer.registerCallableInterceptors(callableProcessingInterceptor);
        //使用DeferredResult进行异步编程时进行拦截
        configurer.registerDeferredResultInterceptors(deferredResultProcessingInterceptor);
        //设置异步超时时间
        configurer.setDefaultTimeout(1000);
        //设置自定义线程工厂
        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(helloInterceptor);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        HelloFilter filter = new HelloFilter();
        filterRegistrationBean.setFilter(filter);
        List<String> urls = new ArrayList<>(2);
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

}
