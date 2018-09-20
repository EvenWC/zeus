package com.wangcheng.zeus.demo.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import java.util.concurrent.Callable;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 13:46
 * @Description: 针对Callable 进行异步编程拦截器
 */
@Component
public class MyCallableProcessingInterceptor implements CallableProcessingInterceptor {
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, Callable<T> task) throws Exception {

    }

    @Override
    public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {

    }

    @Override
    public <T> void postProcess(NativeWebRequest request, Callable<T> task, Object concurrentResult) throws Exception {

    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        return null;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, Callable<T> task) throws Exception {

    }
}
