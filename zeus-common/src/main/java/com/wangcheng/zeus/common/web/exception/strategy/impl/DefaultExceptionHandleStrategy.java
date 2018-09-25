package com.wangcheng.zeus.common.web.exception.strategy.impl;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @Date: 2018/9/4 21:00
 * @Description:
 */
@ExceptionHandleType(value = {Exception.class})
public class DefaultExceptionHandleStrategy implements ExceptionHandleStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResponseModel handle(Exception e) {
        //记录日志
        StackTraceElement[] stackTrace = e.getStackTrace();
        String className = stackTrace[0].getClassName();
        int lineNumber = stackTrace[0].getLineNumber();
        String methodName = stackTrace[0].getMethodName();
        boolean nativeMethod = stackTrace[0].isNativeMethod();
        StringBuffer sb = new StringBuffer();
        sb.append("className:"+className +"\n");
        sb.append("lineNumber:" +lineNumber +"\n");
        sb.append("methodName:" +methodName +"\n");
        sb.append("isNativeMethod:" +nativeMethod +"\n");
        logger.error(sb.toString(),e);
        return ResponseModel.FAIL(e.getMessage());
    }
}
