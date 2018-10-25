package com.wangcheng.zeus.core.config.validate.code.manager.impl;

import com.wangcheng.zeus.core.config.properties.SmsCodeProperties;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.ImageCode;
import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.manager.ValidateCodeManager;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author: Administrator
 * @Date: 2018/9/27 20:52
 * @Description:
 */
public class SmsValidateCodeManager implements ValidateCodeManager {

    private ZeusProperties zeusProperties;


    public SmsValidateCodeManager( ZeusProperties zeusProperties){
        this.zeusProperties = zeusProperties;
    }

    @Override
    public ValidateCode generateValidateCode() {
        SmsCodeProperties smsCode = zeusProperties.getValidateCode().getSmsCode();
        String validateCode = RandomStringUtils.randomNumeric(smsCode.getLength());
        return new ValidateCode(validateCode,smsCode.getExpireIn());
    }

    @Override
    public Boolean checkValidateCode(ValidateCode validateCode, String requestValidateCode) {
        return null;
    }
}
