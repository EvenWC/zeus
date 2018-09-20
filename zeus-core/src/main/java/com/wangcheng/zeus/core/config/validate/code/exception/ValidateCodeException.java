package com.wangcheng.zeus.core.config.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/20 21:45
 * @Description:
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
