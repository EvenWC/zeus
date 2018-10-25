package com.wangcheng.zeus.core.config.validate.code.processor;

import com.wangcheng.zeus.common.web.exception.ValidateException;
import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.exception.ValidateCodeException;
import com.wangcheng.zeus.core.config.validate.code.manager.ValidateCodeManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author : evan
 * @Date: 2018/9/27 21:12
 * @Description:
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String,ValidateCodeManager> validateCodeManagers;


    @Override
    public void createValidateCode(ServletWebRequest servletWebRequest) throws IOException {
        String processorType = getValidateCodeType();
        ValidateCodeManager validateCodeManager = validateCodeManagers.get(processorType + "CodeManager");
        ValidateCode validateCode = validateCodeManager.generateValidateCode();
        saveSession(validateCode,servletWebRequest,processorType);
        sendValidateCode(validateCode,servletWebRequest);
    }

    /**
     * 保存进session
     * @param validateCode
     * @param servletWebRequest
     */
    private void saveSession(ValidateCode validateCode , ServletWebRequest servletWebRequest,String type){
        sessionStrategy.setAttribute(servletWebRequest,SESSION_VALIDATE_CODE_PREFIX + type.toUpperCase(),validateCode);
    }

    /**
     * 发生验证码
     * @param validateCode
     * @param servletWebRequest
     * @throws IOException
     */
    public abstract void sendValidateCode(ValidateCode validateCode , ServletWebRequest servletWebRequest) throws IOException;

    /**
     * 获取验证码类型
     * @return
     */
    public abstract String getValidateCodeType();

    @Override
    public void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        String validateCodeType = getValidateCodeType();
        //从session中获取验证码
        ValidateCode codeInSession = (ValidateCode)sessionStrategy.getAttribute(servletWebRequest, SESSION_VALIDATE_CODE_PREFIX + validateCodeType.toUpperCase());
        //从请求中获取验证码
        String requestCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), validateCodeType + VALIDATE_CODE_PARAMETER_SUFFIX);
        if(StringUtils.isEmpty(requestCode)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpire()){
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equalsIgnoreCase(codeInSession.getCode(),requestCode)){
            throw new ValidateCodeException("验证码不正确");
        }
        //从session中清除验证码
        sessionStrategy.removeAttribute(servletWebRequest,SESSION_VALIDATE_CODE_PREFIX + validateCodeType.toUpperCase());
    }
}
