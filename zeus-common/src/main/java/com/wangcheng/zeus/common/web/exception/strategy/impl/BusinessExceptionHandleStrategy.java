package com.wangcheng.zeus.common.web.exception.strategy.impl;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.BusinessException;
import com.wangcheng.zeus.common.web.exception.strategy.AbstractExceptionHandleStrategy;
import com.wangcheng.zeus.common.web.exception.strategy.BaseExceptionHandlerStrategyAware;
import com.wangcheng.zeus.common.web.exception.strategy.annotation.ExceptionHandleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Administrator
 * @Date: 2018/8/30 21:52
 * @Description: 业务断言的处理策略
 */
@ExceptionHandleType(BusinessException.class)
public class BusinessExceptionHandleStrategy extends AbstractExceptionHandleStrategy implements BaseExceptionHandlerStrategyAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResponseModel doHandle(Throwable e) {
        logger.warn(e.getMessage());
        return ResponseModel.ERROR(e.getMessage());
    }
}
