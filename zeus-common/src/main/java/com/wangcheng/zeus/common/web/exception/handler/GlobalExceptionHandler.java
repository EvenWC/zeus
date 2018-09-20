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
import com.wangcheng.zeus.common.web.exception.factory.ExceptionResponseFactory;
import com.wangcheng.zeus.common.web.exception.strategy.ExceptionHandleStrategy;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalExceptionHandler extends AbstractErrorController {

    private ErrorProperties errorProperties = new ErrorProperties();

    public GlobalExceptionHandler(){
        super(new DefaultErrorAttributes());
    }

    public GlobalExceptionHandler(ErrorAttributes errorAttributes,
                                ErrorProperties errorProperties) {
        this(errorAttributes, errorProperties,
                Collections.<ErrorViewResolver>emptyList());
    }

    public GlobalExceptionHandler(ErrorAttributes errorAttributes,
                                ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseModel handleException(Exception e){
        ExceptionHandleStrategy exceptionHandler = ExceptionResponseFactory.INSTANCE.createExceptionHandler(e);
        return exceptionHandler.handle(e);
    }
    @RequestMapping
    public ResponseModel error(HttpServletRequest request) {
        System.out.println(errorProperties);
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        return ResponseModel.FAIL(Integer.parseInt(status.toString()),(String) body.get("message"));
    }
    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }


    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

}
