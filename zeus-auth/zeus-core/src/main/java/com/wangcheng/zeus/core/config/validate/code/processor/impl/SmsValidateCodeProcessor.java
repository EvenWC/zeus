package com.wangcheng.zeus.core.config.validate.code.processor.impl;

import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.manager.SmsSender;
import com.wangcheng.zeus.core.config.validate.code.manager.ValidateCodeManager;
import com.wangcheng.zeus.core.config.validate.code.processor.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author : Administrator
 * @Date: 2018/9/27 21:26
 * @Description:
 */
@Component("smsCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsSender smsSender;

    @Override
    public void sendValidateCode(ValidateCode validateCode, ServletWebRequest servletWebRequest) throws IOException {

        smsSender.sendSms(validateCode.getCode());
    }

    @Override
    public String getValidateCodeType() {
        return "sms";
    }
}
