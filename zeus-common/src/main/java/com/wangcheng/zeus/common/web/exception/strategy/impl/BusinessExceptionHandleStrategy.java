package com.wangcheng.zeus.common.web.exception.strategy.impl;

import com.wangcheng.zeus.common.constant.ResponseConstant;
import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.BusinessException;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: Administrator
 * @Date: 2018/8/30 21:52
 * @Description: 业务断言的处理策略
 */
@ExceptionHandle(BusinessException.class)
public class BusinessExceptionHandleStrategy implements ExceptionHandleStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResponseModel handle(Exception e) {
        //todo:处理业务异常的逻辑
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            System.out.println("堆栈信息："+stackTrace[i]);
        }
        return ResponseModel.FAIL(ResponseConstant.BUSINESS_CODE,e.getMessage());
    }
}
