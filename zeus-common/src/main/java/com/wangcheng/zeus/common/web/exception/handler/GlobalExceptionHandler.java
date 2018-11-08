/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wangcheng.zeus.common.web.exception.handler;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.common.web.exception.holder.ExceptionHandleStrategyHolder;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author wangcheng
 */
@RestControllerAdvice
@RestController
@RequestMapping("/error")
public class GlobalExceptionHandler implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private  ExceptionHandleStrategyHolder strategyHolder;

    public GlobalExceptionHandler(ExceptionHandleStrategyHolder strategyHolder){
        this.strategyHolder = strategyHolder;
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseModel handleException(HttpServletRequest request, HttpServletResponse response, Throwable e){
        logger.info("开始处理异常："+e.getClass().getName());
        ExceptionHandleStrategy exceptionHandler = strategyHolder.getStrategy(e);
        logger.info("获取异常处理策略："+exceptionHandler.toString());
        return exceptionHandler.handle(request,response,e);
    }

    @GetMapping
    public ResponseModel error(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
        Integer httpStatus = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        if(httpStatus == null){
            httpStatus = 500;
        }
        return ResponseModel.ERROR(httpStatus,errorMessage);
    }
    @Override
    public String getErrorPath() {
        return "error";
    }
}
