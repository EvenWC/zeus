package com.wangcheng.zeus.common.web.exception.strategy;

import com.wangcheng.zeus.common.response.ResponseModel;

/**
 * @Auther: Administrator
 * @Date: 2018/8/30 22:19
 * @Description: 抽象出来处理异常的策略接口
 */
public interface ExceptionHandleStrategy {
    /**
     * 处理异常的接口
     */
    ResponseModel handle(Exception e);

}
