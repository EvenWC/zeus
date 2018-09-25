package com.wangcheng.zeus.demo.web.exception;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandleType;

/**
 * @author : Administrator
 * @Date: 2018/9/24 13:44
 * @Description:
 */
@ExceptionHandleType(Exception.class)
public class ExceptionStrategy implements ExceptionHandleStrategy {

    @Override
    public ResponseModel handle(Exception e) {

        System.out.println(e.getMessage());

        return ResponseModel.FAIL(e.getMessage());
    }
}
