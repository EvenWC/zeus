package com.wangcheng.zeus.demo.web.exception;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.ValidateException;
import com.wangcheng.zeus.common.web.exception.strategy.AbstractExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandleType;

/**
 * @author : Administrator
 * @Date: 2018/9/24 13:44
 * @Description:
 */
@ExceptionHandleType({ValidateException.class})
public class ExceptionStrategy extends AbstractExceptionHandleStrategy {

    @Override
    public ResponseModel doHandle(Throwable e) {

        System.out.println(e.getMessage());

        return ResponseModel.ERROR(e.getMessage());
    }
}
