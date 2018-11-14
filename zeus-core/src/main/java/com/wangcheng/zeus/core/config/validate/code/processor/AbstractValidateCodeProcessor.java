package com.wangcheng.zeus.core.config.validate.code.processor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import com.wangcheng.zeus.core.config.utils.JsonUtils;
import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.exception.ValidateCodeException;
import com.wangcheng.zeus.core.config.validate.code.manager.ValidateCodeManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @author : evan
 * @Date: 2018/9/27 21:12
 * @Description:
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 缓存验证码：key 是token
     * todo:暂时先放到map里面,后续考虑使用redis
     */
    private Map<String,ValidateCode> validateCodeHolder = Maps.newConcurrentMap();

    @Autowired
    private Map<String,ValidateCodeManager> validateCodeManagers;


    @Override
    public void createValidateCode(ServletWebRequest servletWebRequest) throws IOException {
        String processorType = getValidateCodeType();
        ValidateCodeManager validateCodeManager = validateCodeManagers.get(processorType + "CodeManager");
        ValidateCode validateCode = validateCodeManager.generateValidateCode();
        saveValidate(validateCode);
        sendValidateCode(validateCode,servletWebRequest);
    }

    /**
     * 保存验证码
     * @param validateCode
     */
    private void saveValidate(ValidateCode validateCode){
        validateCodeHolder.put(validateCode.getToken(),validateCode);
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
        //从请求中获取验证码和验证码token
        String validateCodeKey = validateCodeType + VALIDATE_CODE_PARAMETER_SUFFIX;
        String validateTokenKey = validateCodeType + VALIDATE_TOKEN_PARAMETER_SUFFIX;
        String contentType = servletWebRequest.getRequest().getContentType();
        String requestCode;
        String validateToken;
        if(StringUtils.startsWithIgnoreCase(contentType,MediaType.APPLICATION_JSON_VALUE)){
            try {
                Map body = JsonUtils.readValue(servletWebRequest.getRequest().getInputStream(), Map.class);
                requestCode = (String) body.get(validateCodeKey);
                validateToken = (String) body.get(validateTokenKey);
            } catch (IOException e) {
                throw new IllegalArgumentException("body is null",e);
            }
        }else{
            requestCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), validateCodeKey);
            validateToken = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), validateTokenKey);
        }
        if(StringUtils.isEmpty(validateToken)){
            throw new IllegalArgumentException(String.format("参数%s不能为空",validateTokenKey));
        }
        //从holder中获取验证码
        ValidateCode validateCodeInHolder = validateCodeHolder.get(validateToken);

        if(StringUtils.isEmpty(requestCode)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if(validateCodeInHolder == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(validateCodeInHolder.isExpire()){
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equalsIgnoreCase(validateCodeInHolder.getCode(),requestCode)){
            throw new ValidateCodeException("验证码不正确");
        }
        //从holder中清除
        validateCodeHolder.remove(validateToken);
    }
}
