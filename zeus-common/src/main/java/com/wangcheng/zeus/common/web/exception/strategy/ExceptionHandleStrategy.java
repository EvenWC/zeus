package com.wangcheng.zeus.common.web.exception.strategy;

import com.wangcheng.zeus.common.response.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Administrator
 * @Date: 2018/8/30 22:19
 * @Description: 抽象出来处理异常的策略接口
 */
public interface ExceptionHandleStrategy {
    /**
     * 处理异常的接口
     * @param request
     * @param response
     * @param e
     * @return
     */
    ResponseModel handle(HttpServletRequest request, HttpServletResponse response,Throwable e);

}
