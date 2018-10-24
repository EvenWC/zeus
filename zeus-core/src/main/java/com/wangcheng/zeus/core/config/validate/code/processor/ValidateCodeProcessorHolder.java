package com.wangcheng.zeus.core.config.validate.code.processor;

import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018/10/10 21:03
 * @description:
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessorMap;

    /**
     * 通过类型获得一个ValidateCodeProcessor
     * @param type
     * @return
     */
    public ValidateCodeProcessor getValidateCodeProcessorByType(ValidateCodeType type){
        String beanName = type.toString().toLowerCase() + "CodeProcessor";
        return validateCodeProcessorMap.get(beanName);
    }


}
