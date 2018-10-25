package com.wangcheng.zeus.core.config.validate.code.processor;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author : evan
 * @Date: 2018/9/27 21:07
 * @Description: 创建验证码处理流程
 */
public interface ValidateCodeProcessor {

    /**
     * 保存进session key的前缀
     */
    String SESSION_VALIDATE_CODE_PREFIX = "SESSION_VALIDATE_CODE_";
    /**
     * 验证码请求参数后缀
     */
    String VALIDATE_CODE_PARAMETER_SUFFIX = "Code";

    /**
     * 创建验证码流程
     * @param servletWebRequest
     * @throws IOException
     */
    void createValidateCode(ServletWebRequest servletWebRequest) throws IOException;

    /**
     * 验证验证码
     * @param servletWebRequest
     * @throws ServletRequestBindingException
     */
    void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException;
}
