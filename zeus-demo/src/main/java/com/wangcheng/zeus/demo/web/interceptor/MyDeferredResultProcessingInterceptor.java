package com.wangcheng.zeus.demo.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 13:47
 * @Description: 使用DeferredResult进行异步编程时使用
 */
@Component
public class MyDeferredResultProcessingInterceptor implements DeferredResultProcessingInterceptor {
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {

    }

    @Override
    public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {

    }

    @Override
    public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult) throws Exception {

    }

    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        return false;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {

    }
}
